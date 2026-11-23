package com.example.AcademicProgressRecord_version12.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val targetScore: Int,
    val minAttendancePercent: Int,
    val status: String,
    val currentScore: Double,
    val attendancePercent: Int
)

@Entity(tableName = "components", indices = [Index("subjectId")])
data class ComponentEntity(
    @PrimaryKey val id: Int,
    val subjectId: Int,
    val name: String,
    val weight: Double,
    val maxScore: Double,
    val score: Double?
)