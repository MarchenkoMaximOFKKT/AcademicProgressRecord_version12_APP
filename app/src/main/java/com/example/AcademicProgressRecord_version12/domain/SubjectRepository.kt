package com.example.AcademicProgressRecord_version12.domain

import com.example.AcademicProgressRecord_version12.Subject

interface SubjectRepository {
    suspend fun getSubjects(): List<Subject>
}