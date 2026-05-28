package com.example.data.repository

import com.example.data.model.TrackedMedication

data class CommonMedication(
    val id: String,
    val name: String,
    val category: String,
    val standardDoses: List<String>,
    val indications: String,
    val warnings: String,
    val interactions: List<InteractionRule>
)

data class InteractionRule(
    val interactingWithId: String,
    val interactingWithName: String,
    val severity: String, // "High Risk", "Moderate Attention", "Precaution"
    val explanation: String
)

object OfflineMedications {

    val medications = listOf(
        CommonMedication(
            id = "aspirin",
            name = "Aspirin",
            category = "NSAID / Antiplatelet",
            standardDoses = listOf("81 mg", "325 mg", "500 mg"),
            indications = "Prevention of stroke and myocardial infarction, acute management of heart attacks, mild pain, and fever.",
            warnings = "Take with food to minimize stomach upset. Avoid in children with viral illness due to risk of Reye's Syndrome.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "ibuprofen",
                    interactingWithName = "Ibuprofen",
                    severity = "Moderate Attention",
                    explanation = "Ibuprofen can block Aspirin's antiplatelet cardioprotective effect if taken simultaneously, and increases risk of gastrointestinal ulcers."
                ),
                InteractionRule(
                    interactingWithId = "warfarin",
                    interactingWithName = "Warfarin",
                    severity = "High Risk",
                    explanation = "Combining anticoagulants with antiplatelets significantly elevates the risk of life-threatening internal bleeding/hemorrhage."
                )
            )
        ),
        CommonMedication(
            id = "ibuprofen",
            name = "Ibuprofen",
            category = "NSAID (Nonsteroidal Anti-inflammatory)",
            standardDoses = listOf("200 mg", "400 mg", "600 mg", "800 mg"),
            indications = "Management of mild-to-moderate pain, fever, menstrual cramps, osteoarthritis, and inflammatory arthritis.",
            warnings = "May cause gastric ulcers, kidney strain, or elevated blood pressure with chronic or high-dose usage.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "aspirin",
                    interactingWithName = "Aspirin",
                    severity = "Moderate Attention",
                    explanation = "Congruent use of alternative NSAIDs expands gastric mucosal erosion risk and reduces aspirin's cardioprotection."
                ),
                InteractionRule(
                    interactingWithId = "lisinopril",
                    interactingWithName = "Lisinopril",
                    severity = "Moderate Attention",
                    explanation = "NSAIDs reduce renal prostaglandins. This can trigger acute kidney impairment and blunt Lisinopril's blood pressure dampening."
                )
            )
        ),
        CommonMedication(
            id = "lisinopril",
            name = "Lisinopril",
            category = "ACE Inhibitor",
            standardDoses = listOf("5 mg", "10 mg", "20 mg", "40 mg"),
            indications = "Treatment of hypertension, heart failure management, and preserving kidney function post-myocardial infarction.",
            warnings = "Can cause a persistent dry, hacking tickling cough. Monitor blood pressure and renal function periodically.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "spironolactone",
                    interactingWithName = "Spironolactone",
                    severity = "High Risk",
                    explanation = "Concomitant use of ACE inhibitors and potassium-sparing diuretics results in cumulative potassium retention (hyperkalemia), potentially inducing cardiac arrhythmias."
                ),
                InteractionRule(
                    interactingWithId = "ibuprofen",
                    interactingWithName = "Ibuprofen",
                    severity = "Moderate Attention",
                    explanation = "NSAIDs blunt vasodilatory effects of ACE inhibitors and place high stress on glomerular renal filtration."
                )
            )
        ),
        CommonMedication(
            id = "spironolactone",
            name = "Spironolactone",
            category = "Potassium-Sparing Diuretic",
            standardDoses = listOf("25 mg", "50 mg", "100 mg"),
            indications = "Management of heart failure, hyperaldosteronism, fluid retention/edema, and secondary hypertension treatment.",
            warnings = "Requires periodic blood testing to check potassium levels. May cause breast tenderness or enlargement in men.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "lisinopril",
                    interactingWithName = "Lisinopril",
                    severity = "High Risk",
                    explanation = "Lisinopril limits aldosterone release while Spironolactone directly blocks potassium excretion, heightening risk of severe hyperkalemia."
                )
            )
        ),
        CommonMedication(
            id = "nitroglycerin",
            name = "Nitroglycerin",
            category = "Vasodilator / Nitrate",
            standardDoses = listOf("0.4 mg sublingual", "0.1 mg/hr patch"),
            indications = "Prevention or acute treatment of chest pain (angina pectoris) linked to coronary artery disease.",
            warnings = "Sit down before taking sublingual tablets as they can cause rapid orthostatic blood pressure drops and head rush.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "sildenafil",
                    interactingWithName = "Sildenafil (Viagra)",
                    severity = "High Risk",
                    explanation = "Both drugs dilate major blood vessels via nitric oxide pathways. Co-administration can cause catastrophic, life-threatening hypotension (shock)."
                )
            )
        ),
        CommonMedication(
            id = "sildenafil",
            name = "Sildenafil (Viagra)",
            category = "PDE5 Inhibitor",
            standardDoses = listOf("25 mg", "50 mg", "100 mg"),
            indications = "Treatment of erectile dysfunction and pulmonary arterial hypertension.",
            warnings = "Do not take with nitrates. Contact a physician immediately if an erection persists longer than 4 hours.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "nitroglycerin",
                    interactingWithName = "Nitroglycerin",
                    severity = "High Risk",
                    explanation = "Dual arterial system dilation causes sudden, severe cardiovascular volume collapse."
                )
            )
        ),
        CommonMedication(
            id = "albuterol",
            name = "Albuterol (ProAir)",
            category = "Short-Acting Beta-2 Agonist",
            standardDoses = listOf("90 mcg Inhaler", "2 mg"),
            indications = "Relief and prevention of bronchospasm in patients with asthma or COPD.",
            warnings = "May cause mild hand trembles, rapid pulse, or nervousness. Always keep a rescue inhaler readily nearby.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "propranolol",
                    interactingWithName = "Propranolol",
                    severity = "High Risk",
                    explanation = "Beta-blockers can cause bronchospasm and strongly antagonize/negate the airway-opening bronchodilation produced by Albuterol."
                )
            )
        ),
        CommonMedication(
            id = "propranolol",
            name = "Propranolol",
            category = "Non-Selective Beta-Blocker",
            standardDoses = listOf("10 mg", "20 mg", "40 mg", "85 mg"),
            indications = "Treats chest pain, hypertension, tremors, migraine prevention, and cardiac arrhythmias.",
            warnings = "Can cause slowed heart rate or fatigue. Do not discontinue abruptly without consultant warning.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "albuterol",
                    interactingWithName = "Albuterol",
                    severity = "High Risk",
                    explanation = "Unselective blockade of beta-2 receptors in the lungs completely triggers severe asthma or blocks fast relief."
                )
            )
        ),
        CommonMedication(
            id = "warfarin",
            name = "Warfarin (Coumadin)",
            category = "Vitamin K Antagonist Anticoagulant",
            standardDoses = listOf("1 mg", "2 mg", "5 mg"),
            indications = "Disrupts blood clotting to prevent pulmonary embolism, deep vein thrombosis, and strokes in atrial fibrillation.",
            warnings = "Mandates precise regular INR blood coagulation measurement. Very sensitive to changes in green vegetable intake.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "aspirin",
                    interactingWithName = "Aspirin",
                    severity = "High Risk",
                    explanation = "Synergistic anticoagulant / antiplatelet effects break safe homeostasis limits, raising risk of severe gastrointestinal or cranial hemorrhage."
                )
            )
        ),
        CommonMedication(
            id = "omeprazole",
            name = "Omeprazole (Prilosec)",
            category = "Proton Pump Inhibitor (PPI)",
            standardDoses = listOf("20 mg", "40 mg"),
            indications = "Treatment of acid reflux, GERD, and stomach ulcers.",
            warnings = "Ideally take 30-60 minutes before the first meal of the day with water.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "clopidogrel",
                    interactingWithName = "Clopidogrel (Plavix)",
                    severity = "Moderate Attention",
                    explanation = "Omeprazole impairs hepatic CYP2C19 enzymes which activates Clopidogrel, making the anti-platelet treatment less effective at preventing blood clots."
                ),
                InteractionRule(
                    interactingWithId = "levothyroxine",
                    interactingWithName = "Levothyroxine",
                    severity = "Precaution",
                    explanation = "Reduced gastric acid limits absorption of calcium carbonate or similar supplements, but PPIs also alter gastric pH slowing absorbability of thyroid hormones."
                )
            )
        ),
        CommonMedication(
            id = "clopidogrel",
            name = "Clopidogrel (Plavix)",
            category = "Thienopyridine Antiplatelet",
            standardDoses = listOf("75 mg"),
            indications = "Prevents stroke, heart attacks, and blood clots in high-risk patients with cardiac stents or blockages.",
            warnings = "Observe for nosebleeds or dark bruises. Report unexplained or long-term bleeding to a physician.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "omeprazole",
                    interactingWithName = "Omeprazole",
                    severity = "Moderate Attention",
                    explanation = "Omeprazole reduces the activation rate of Clopidogrel. Consider Pantoprazole as a safer alternative if a PPI is required."
                )
            )
        ),
        CommonMedication(
            id = "metformin",
            name = "Metformin (Glucophage)",
            category = "Biguanide Oral Antidiabetic",
            standardDoses = listOf("500 mg", "850 mg", "1000 mg"),
            indications = "First-line oral treatment for Type 2 Diabetes Mellitus to lower blood glucose and improve insulin sensitivity.",
            warnings = "Take with meals to reduce gastrointestinal distress. Stay well-hydrated.",
            interactions = listOf() // Contrast concerns are clinical, no specific overlapping tracked medicine id
        ),
        CommonMedication(
            id = "levothyroxine",
            name = "Levothyroxine (Synthroid)",
            category = "Synthetic Thyroid Hormone (T4)",
            standardDoses = listOf("25 mcg", "50 mcg", "75 mcg", "100 mcg", "125 mcg"),
            indications = "Replacement therapy for hypothyroidism (underactive thyroid gland).",
            warnings = "Must take on an empty stomach in the morning 30-60 minutes before any food, coffee, or calcium pills.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "calcium_carbonate",
                    interactingWithName = "Calcium Carbonate",
                    severity = "Moderate Attention",
                    explanation = "Calcium directly binds Levothyroxine in the gut, severely preventing absorption. Must separate administration by at least 4 hours."
                )
            )
        ),
        CommonMedication(
            id = "calcium_carbonate",
            name = "Calcium Carbonate (Tums / Caltrate)",
            category = "Calcium Supplement / Antacid",
            standardDoses = listOf("500 mg", "1000 mg"),
            indications = "Prevention or treatment of osteoporosis, calcium deficiency, and rapid relief of acid indigestion/heartburn.",
            warnings = "Can cause mild constipation. Do not exceed the maximum daily count.",
            interactions = listOf(
                InteractionRule(
                    interactingWithId = "levothyroxine",
                    interactingWithName = "Levothyroxine",
                    severity = "Moderate Attention",
                    explanation = "Calcium salts decrease absorption of Levothyroxine. Ensure wide dosage separation."
                )
            )
        )
    )

    fun getMedicationById(id: String): CommonMedication? {
        return medications.find { it.id == id }
    }

    fun findMatchingCommonMedication(inputName: String): CommonMedication? {
        val trimmed = inputName.trim()
        // Try searching for exact name or matching a substring
        return medications.find {
            it.name.contains(trimmed, ignoreCase = true) ||
            trimmed.contains(it.name, ignoreCase = true)
        }
    }

    /**
     * Checks for therapeutic interactions amongst a list of currently tracked medicaments.
     * Returns a list of alert details (InteractionPairAlert) outlining findings.
     */
    fun checkInteractions(currentTracked: List<TrackedMedication>): List<InteractionAlertResult> {
        val results = mutableListOf<InteractionAlertResult>()
        if (currentTracked.size < 2) return results

        // Pairwise comparison of all tracked items
        for (i in 0 until currentTracked.size) {
            val medA = currentTracked[i]
            val commonA = findMatchingCommonMedication(medA.name) ?: continue

            for (j in i + 1 until currentTracked.size) {
                val medB = currentTracked[j]
                val commonB = findMatchingCommonMedication(medB.name) ?: continue

                // Check if commonA lists commonB as interacting
                val rule1 = commonA.interactions.find { it.interactingWithId == commonB.id }
                if (rule1 != null) {
                    results.add(
                        InteractionAlertResult(
                            medicationNameA = medA.name,
                            medicationNameB = medB.name,
                            severity = rule1.severity,
                            explanation = rule1.explanation
                        )
                    )
                    continue
                }

                // Check if commonB lists commonA as interacting
                val rule2 = commonB.interactions.find { it.interactingWithId == commonA.id }
                if (rule2 != null) {
                    results.add(
                        InteractionAlertResult(
                            medicationNameA = medA.name,
                            medicationNameB = medB.name,
                            severity = rule2.severity,
                            explanation = rule2.explanation
                        )
                    )
                }
            }
        }
        return results
    }
}

data class InteractionAlertResult(
    val medicationNameA: String,
    val medicationNameB: String,
    val severity: String,
    val explanation: String
)
