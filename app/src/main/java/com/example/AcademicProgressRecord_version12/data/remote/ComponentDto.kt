package com.example.AcademicProgressRecord_version12.data.remote

import com.google.gson.annotations.SerializedName

data class ComponentDto(
    val id: Int,
    @SerializedName("subject_id") val subjectId: Int,
    val name: String,
    val weight: Double,
    @SerializedName("max_score") val maxScore: Double,
    val score: Double?
)