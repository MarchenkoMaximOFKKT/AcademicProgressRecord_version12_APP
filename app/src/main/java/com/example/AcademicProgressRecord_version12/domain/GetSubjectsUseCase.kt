package com.example.AcademicProgressRecord_version12.domain

import com.example.AcademicProgressRecord_version12.Subject

class GetSubjectsUseCase(private val repository: SubjectRepository) {
    suspend operator fun invoke(): List<Subject> = repository.getSubjects()
}