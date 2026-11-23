package com.example.AcademicProgressRecord_version12.data.remote

import com.example.AcademicProgressRecord_version12.Subject
import com.example.AcademicProgressRecord_version12.SubjectStatus
import com.google.gson.annotations.SerializedName

data class SubjectDto(
    val id: Int,
    val name: String,
    @SerializedName("target_score") val targetScore: Int,
    @SerializedName("min_attendance_percent") val minAttendancePercent: Int,
    val status: String,
    @SerializedName("current_score") val currentScore: Double,
    @SerializedName("attendance_percent") val attendancePercent: Int
)
fun SubjectDto.toDomain(): Subject = Subject(
    id = id,
    name = name,
    targetScore = targetScore,
    minAttendancePercent = minAttendancePercent,
    status = SubjectStatus.values().find { it.name == status } ?: SubjectStatus.SAFE,
    currentScore = currentScore,
    attendancePercent = attendancePercent
)
