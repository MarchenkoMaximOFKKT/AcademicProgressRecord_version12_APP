package com.example.AcademicProgressRecord_version12.data

import com.example.AcademicProgressRecord_version12.GradeComponent
import com.example.AcademicProgressRecord_version12.Subject
import com.example.AcademicProgressRecord_version12.SubjectStatus
import com.example.AcademicProgressRecord_version12.data.local.ComponentEntity
import com.example.AcademicProgressRecord_version12.data.local.SubjectEntity
import com.example.AcademicProgressRecord_version12.data.remote.ComponentDto
import com.example.AcademicProgressRecord_version12.data.remote.SubjectDto

fun SubjectDto.toEntity() = SubjectEntity(
    id, name, targetScore, minAttendancePercent, status, currentScore, attendancePercent
)

fun ComponentDto.toEntity() = ComponentEntity(id, subjectId, name, weight, maxScore, score)

fun SubjectEntity.toDomain() = Subject(
    id = id,
    name = name,
    targetScore = targetScore,
    minAttendancePercent = minAttendancePercent,
    status = SubjectStatus.entries.find { it.name == status } ?: SubjectStatus.SAFE,
    currentScore = currentScore,
    attendancePercent = attendancePercent
)

fun ComponentEntity.toDomain() = GradeComponent(id, subjectId, name, weight, maxScore, score)