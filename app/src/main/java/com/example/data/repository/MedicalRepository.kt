package com.example.data.repository

import com.example.BuildConfig
import com.example.api.Content
import com.example.api.GenerateContentRequest
import com.example.api.GenerationConfig
import com.example.api.Part
import com.example.api.RetrofitClient
import com.example.data.database.MedicalDao
import com.example.data.model.BookmarkedCondition
import com.example.data.model.MedicalCondition
import com.example.data.model.SymptomCheckHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MedicalRepository(private val medicalDao: MedicalDao) {

    private val conditionAdapter by lazy {
        RetrofitClient.moshiInstance.adapter(MedicalCondition::class.java)
    }

    // --- Bookmarks Management ---
    val bookmarkedConditions: Flow<List<MedicalCondition>> = medicalDao.getAllBookmarks().map { entityList ->
        entityList.mapNotNull { entity ->
            try {
                conditionAdapter.fromJson(entity.serializedContent)
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun toggleBookmark(condition: MedicalCondition) = withContext(Dispatchers.IO) {
        val isBookmarked = medicalDao.getBookmarkById(condition.id) != null
        if (isBookmarked) {
            medicalDao.deleteBookmarkById(condition.id)
        } else {
            val serialized = conditionAdapter.toJson(condition)
            val entity = BookmarkedCondition(
                id = condition.id,
                name = condition.name,
                category = condition.category,
                serializedContent = serialized
            )
            medicalDao.insertBookmark(entity)
        }
    }

    suspend fun isBookmarked(conditionId: String): Boolean = withContext(Dispatchers.IO) {
        medicalDao.getBookmarkById(conditionId) != null
    }

    // --- Tracked Medications ---
    val trackedMedications: Flow<List<com.example.data.model.TrackedMedication>> = medicalDao.getAllTrackedMedications()

    suspend fun addTrackedMedication(name: String, dosage: String, timing: String, notes: String) = withContext(Dispatchers.IO) {
        val med = com.example.data.model.TrackedMedication(
            name = name,
            dosage = dosage,
            timing = timing,
            notes = notes
        )
        medicalDao.insertTrackedMedication(med)
    }

    suspend fun deleteTrackedMedication(id: Int) = withContext(Dispatchers.IO) {
        medicalDao.deleteTrackedMedicationById(id)
    }

    // --- Symptom Check History ---
    val symptomHistory: Flow<List<SymptomCheckHistory>> = medicalDao.getAllHistory()

    suspend fun saveSymptomCheck(inputSymptoms: String, analysisSummary: String, isSevere: Boolean) = withContext(Dispatchers.IO) {
        val entry = SymptomCheckHistory(
            inputSymptoms = inputSymptoms,
            analysisSummary = analysisSummary,
            severeWarningFlag = isSevere
        )
        medicalDao.insertHistory(entry)
    }

    suspend fun deleteHistoryItem(id: Int) = withContext(Dispatchers.IO) {
        medicalDao.deleteHistoryById(id)
    }

    suspend fun clearHistory() = withContext(Dispatchers.IO) {
        medicalDao.clearAllHistory()
    }

    // --- Offline Search & Retrieval ---
    fun searchOffline(query: String): List<MedicalCondition> {
        if (query.isBlank()) return OfflineConditions.conditions
        return OfflineConditions.conditions.filter {
            it.name.contains(query, ignoreCase = true) ||
            it.category.contains(query, ignoreCase = true) ||
            it.symptoms.any { symptom -> symptom.contains(query, ignoreCase = true) }
        }
    }

    fun getConditionOffline(id: String): MedicalCondition? {
        return OfflineConditions.getConditionById(id)
    }

    // --- Dynamic AI Generation (All Medical Conditions) ---
    suspend fun fetchConditionDynamically(query: String): MedicalCondition? = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext null
        }

        val prompt = """
            Generate detailed, structurally intact, evidence-based medical information for the condition: "$query".
            You MUST respond with a single, valid JSON object corresponding EXACTLY to this schema:
            {
              "id": "lowercase_snake_case_name",
              "name": "Full Clinical Name of Condition",
              "category": "One of: Cardiovascular, Respiratory, Gastrointestinal, Endocrine, Dermatology, Neurology, Musculoskeletal, Infectious Diseases, Miscellaneous",
              "shortDescription": "One line summary of the condition",
              "fullDescription": "Comprehensive paragraph describing pathophysiology and clinical definition",
              "symptoms": ["Symptom 1", "Symptom 2", "Symptom 3", "Symptom 4", "Symptom 5"],
              "firstLineTreatments": ["Evidence-based first line treatment 1", "Evidence-based first line treatment 2", "Lifestyle adjustments"],
              "prescriptionTreatments": ["Prescription medications or therapies typically recommended by specialists", "Required diagnostic tests"],
              "selfCare": ["Home modifications, dietary elements, rest, triggers to avoid"],
              "warningSigns": ["Alert warnings or critical complications requiring an immediate emergency visit"],
              "evidenceReference": "Identify the standard medical guidelines used (e.g. ACC/AHA, AAD, AAFP, WHO consensus)"
            }
            Do not include any greeting or conversational fluff outside the JSON.
            Do not put html formatting in the fields.
            Return ONLY clean JSON code.
        """.trimIndent()

        val request = GenerateContentRequest(
            contents = listOf(Content(parts = listOf(Part(text = prompt)))),
            generationConfig = GenerationConfig(
                responseMimeType = "application/json",
                temperature = 0.2f
            )
        )

        try {
            val response = RetrofitClient.service.generateContent(
                model = "gemini-3.5-flash",
                apiKey = apiKey,
                request = request
            )
            val jsonText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            if (jsonText != null) {
                val cleanedJson = cleanJsonString(jsonText)
                conditionAdapter.fromJson(cleanedJson)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // --- Dynamic AI Symptom Checker ---
    suspend fun analyzeSymptomsDynamically(symptoms: String): String? = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext null
        }

        val prompt = """
            You are an expert, empathetic Clinical Symptom Checker AI. Act as an assistant helping triage symptoms. The user reports these symptoms: "$symptoms".
            Analyze this clinical presentation and output your comprehensive assessment in a highly readable format with these sections:
            
            🚨 **CRITICAL SAFETY WARNING**
            If high-emergency signs or life-threatening flags are detected (e.g. chest pain, major dyspnea, sudden numbness/paralysis, severe allergic throat swelling), output a prominent bold emergency warning stating they must seek immediate emergency care (911 / ER). If not, state "No critical emergency flags immediately identified based on description, but monitor closely."
            
            📋 **PRIMARY EXPLORATORY HYPOTHESES**
            List 2 to 3 most common potential medical conditions matching this symptom description, with a brief, evidence-based clinical rationale explaining why they match.
            
            🩺 **NEXT QUESTIONS A PHYSICIAN WOULD ASK**
            List 3 crucial clinical follow-up questions the patient should consider or answer to help narrow this down.
            
            📚 **EVIDENCE-BASED COMFORT & FIRST STEPS**
            Safe, evidence-based supportive guidelines (lifestyle, hydration, rest, safe OTC actions if applicable).
            
            ⚠️ **LEGAL & CLINICAL DISCLAIMER**
            A strong, visible, standard medical disclaimer stating that this AI triage does not constitute a formal diagnosis, does not replace licensed medical consultations, and is solely for informative triage.
            
            Structure the response using simple, clean Markdown. Keep the tone comforting, reassuring, and highly professional.
        """.trimIndent()

        val request = GenerateContentRequest(
            contents = listOf(Content(parts = listOf(Part(text = prompt)))),
            generationConfig = GenerationConfig(temperature = 0.3f)
        )

        try {
            val response = RetrofitClient.service.generateContent(
                model = "gemini-3.5-flash",
                apiKey = apiKey,
                request = request
            )
            response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
        } catch (e: Exception) {
            e.printStackTrace()
            "Error performing symptom analysis: ${e.localizedMessage}. Please verify your Internet connection and API configured."
        }
    }

    private fun cleanJsonString(raw: String): String {
        var str = raw.trim()
        if (str.startsWith("```json")) {
            str = str.substringAfter("```json")
        }
        if (str.startsWith("```")) {
            str = str.substringAfter("```")
        }
        if (str.endsWith("```")) {
            str = str.substringBeforeLast("```")
        }
        return str.trim()
    }
}
