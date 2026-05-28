package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.model.MedicalCondition
import com.example.data.model.SymptomCheckHistory
import com.example.data.model.TrackedMedication
import com.example.data.repository.InteractionAlertResult
import com.example.data.repository.MedicalRepository
import com.example.data.repository.OfflineMedications
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MedicalViewModel(private val repository: MedicalRepository) : ViewModel() {

    // --- Search & Handbook States ---
    val searchQuery = MutableStateFlow("")

    val offlineConditions: StateFlow<List<MedicalCondition>> = searchQuery
        .map { query -> repository.searchOffline(query) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Bookmarks Flow
    val bookmarkedConditions: StateFlow<List<MedicalCondition>> = repository.bookmarkedConditions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    // --- Detailed Condition State (Handles Offline & Dynamic AI fallback) ---
    private val _selectedConditionDetail = MutableStateFlow<MedicalCondition?>(null)
    val selectedConditionDetail: StateFlow<MedicalCondition?> = _selectedConditionDetail.asStateFlow()

    private val _isDetailLoading = MutableStateFlow(false)
    val isDetailLoading: StateFlow<Boolean> = _isDetailLoading.asStateFlow()

    private val _detailError = MutableStateFlow<String?>(null)
    val detailError: StateFlow<String?> = _detailError.asStateFlow()

    private val _isBookmarkedState = MutableStateFlow(false)
    val isBookmarkedState: StateFlow<Boolean> = _isBookmarkedState.asStateFlow()

    fun selectCondition(conditionId: String, dynamicQueryFallback: String? = null) {
        viewModelScope.launch {
            _isDetailLoading.value = true
            _detailError.value = null
            _selectedConditionDetail.value = null

            // First check offline
            val offlineMatch = repository.getConditionOffline(conditionId)
            if (offlineMatch != null) {
                _selectedConditionDetail.value = offlineMatch
                _isBookmarkedState.value = repository.isBookmarked(offlineMatch.id)
                _isDetailLoading.value = false
                return@launch
            }

            // Fallback to dynamic Gemini fetch if allowed/key configured
            val fallbackStr = dynamicQueryFallback ?: conditionId
            val result = repository.fetchConditionDynamically(fallbackStr)
            if (result != null) {
                _selectedConditionDetail.value = result
                _isBookmarkedState.value = repository.isBookmarked(result.id)
            } else {
                _detailError.value = "Failed to load detailed clinical statistics for \"$fallbackStr\". Please ensure you have configured your GEMINI_API_KEY in the Secrets panel."
            }
            _isDetailLoading.value = false
        }
    }

    fun toggleBookmark(condition: MedicalCondition) {
        viewModelScope.launch {
            repository.toggleBookmark(condition)
            _isBookmarkedState.value = repository.isBookmarked(condition.id)
        }
    }


    // --- Symptom Checker States ---
    val symptomInput = MutableStateFlow("")
    
    private val _isSymptomChecking = MutableStateFlow(false)
    val isSymptomChecking: StateFlow<Boolean> = _isSymptomChecking.asStateFlow()

    private val _symptomAnalysis = MutableStateFlow<String?>(null)
    val symptomAnalysis: StateFlow<String?> = _symptomAnalysis.asStateFlow()

    val symptomHistory: StateFlow<List<SymptomCheckHistory>> = repository.symptomHistory
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun RunSymptomCheck() {
        val input = symptomInput.value.trim()
        if (input.isBlank()) return

        viewModelScope.launch {
            _isSymptomChecking.value = true
            _symptomAnalysis.value = null

            val result = repository.analyzeSymptomsDynamically(input)
            _symptomAnalysis.value = result

            if (result != null) {
                // Determine if severe danger warning was given
                val isSevereAlert = result.contains("911", ignoreCase = true) || 
                                    result.contains("emergency", ignoreCase = true) || 
                                    result.contains("life-threatening", ignoreCase = true)
                repository.saveSymptomCheck(
                    inputSymptoms = input,
                    analysisSummary = result,
                    isSevere = isSevereAlert
                )
            }
            _isSymptomChecking.value = false
        }
    }

    fun deleteHistoryItem(id: Int) {
        viewModelScope.launch {
            repository.deleteHistoryItem(id)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearHistory()
        }
    }


    // --- Medication Tracker States (New Core Feature) ---
    val trackedMedications: StateFlow<List<TrackedMedication>> = repository.trackedMedications
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Dynamically computed interactions flow
    val interactionAlerts: StateFlow<List<InteractionAlertResult>> = trackedMedications
        .map { medications -> OfflineMedications.checkInteractions(medications) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Form inputs
    val medNameInput = MutableStateFlow("")
    val medDosageInput = MutableStateFlow("")
    val medTimingInput = MutableStateFlow("")
    val medNotesInput = MutableStateFlow("")

    fun setMedicationNameFromCommon(name: String) {
        medNameInput.value = name
        // Pre-fill standard configurations if found in common database
        val matched = OfflineMedications.findMatchingCommonMedication(name)
        if (matched != null) {
            medDosageInput.value = matched.standardDoses.firstOrNull() ?: ""
            medNotesInput.value = matched.warnings
        }
    }

    fun addMedication() {
        val name = medNameInput.value.trim()
        val dosage = medDosageInput.value.trim()
        val timing = medTimingInput.value.trim()
        val notes = medNotesInput.value.trim()

        if (name.isBlank() || dosage.isBlank() || timing.isBlank()) return

        viewModelScope.launch {
            repository.addTrackedMedication(
                name = name,
                dosage = dosage,
                timing = timing,
                notes = notes
            )
            // Reset fields
            medNameInput.value = ""
            medDosageInput.value = ""
            medTimingInput.value = ""
            medNotesInput.value = ""
        }
    }

    fun removeMedication(id: Int) {
        viewModelScope.launch {
            repository.deleteTrackedMedication(id)
        }
    }
}

class MedicalViewModelFactory(private val repository: MedicalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MedicalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
