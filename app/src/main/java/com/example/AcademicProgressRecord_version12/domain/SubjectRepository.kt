package com.example.AcademicProgressRecord_version12.domain

import com.example.AcademicProgressRecord_version12.GradeComponent
import com.example.AcademicProgressRecord_version12.Result
import com.example.AcademicProgressRecord_version12.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
    fun observeSubjects(): Flow<List<Subject>>
    fun observeSubject(id: Int): Flow<Subject?>
    fun observeComponents(subjectId: Int): Flow<List<GradeComponent>>
    suspend fun refresh(): Result<Unit>
}