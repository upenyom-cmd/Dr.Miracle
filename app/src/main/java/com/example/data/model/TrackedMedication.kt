package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracked_medications")
data class TrackedMedication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dosage: String,
    val timing: String, // e.g. "8:00 AM", "Night", "With Meals"
    val notes: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
