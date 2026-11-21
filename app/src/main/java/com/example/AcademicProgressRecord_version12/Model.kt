package com.example.AcademicProgressRecord_version12
enum class SubjectStatus { SAFE, AT_RISK, CRITICAL }
enum class AttendanceStatus { PRESENT, ABSENT, EXCUSED }
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

data class Subject(
    val id: Int,
    val name: String,
    val targetScore: Int,
    val minAttendancePercent: Int = 75,
    val status: SubjectStatus = SubjectStatus.SAFE,
    val currentScore: Double = 0.0,
    val attendancePercent: Int = 100
)

data class GradeComponent(
    val id: Int,
    val subjectId: Int,
    val name: String,
    val weight: Double,
    val maxScore: Double,
    val score: Double? = null
)

data class ClassSession(
    val id: Int,
    val subjectId: Int,
    val date: String,
    val status: AttendanceStatus
)