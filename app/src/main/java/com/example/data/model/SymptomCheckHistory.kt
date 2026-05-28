package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "symptom_check_history")
data class SymptomCheckHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val inputSymptoms: String,
    val analysisSummary: String, // Dynamic HTML/Markdown feedback from AI Symptom Checker
    val severeWarningFlag: Boolean // Flag if high-emergency elements were uncovered
)
