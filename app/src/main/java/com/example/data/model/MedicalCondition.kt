package com.example.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MedicalCondition(
    val id: String,
    val name: String,
    val category: String,
    val shortDescription: String,
    val fullDescription: String,
    val symptoms: List<String>,
    val firstLineTreatments: List<String>,
    val prescriptionTreatments: List<String>,
    val selfCare: List<String>,
    val warningSigns: List<String>,
    val evidenceReference: String
)

@JsonClass(generateAdapter = true)
data class CategoryInfo(
    val name: String,
    val description: String,
    val iconName: String
)
