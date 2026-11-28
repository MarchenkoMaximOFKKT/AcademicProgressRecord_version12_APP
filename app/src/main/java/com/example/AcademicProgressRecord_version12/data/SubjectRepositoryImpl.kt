package com.example.AcademicProgressRecord_version12.data

import com.example.AcademicProgressRecord_version12.Subject
import com.example.AcademicProgressRecord_version12.domain.SubjectRepository
import com.example.AcademicProgressRecord_version12.sampleSubjects
import kotlinx.coroutines.delay

class SubjectRepositoryImpl : SubjectRepository {
    override suspend fun getSubjects(): List<Subject> {
        delay(800)
        return sampleSubjects
    }
}