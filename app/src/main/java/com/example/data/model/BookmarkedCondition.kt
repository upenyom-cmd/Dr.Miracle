package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_conditions")
data class BookmarkedCondition(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val serializedContent: String, // Full JSON serialization of MedicalCondition
    val timestamp: Long = System.currentTimeMillis()
)
