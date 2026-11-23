package com.example.AcademicProgressRecord_version12.data

import android.util.Log
import com.example.AcademicProgressRecord_version12.Subject
import com.example.AcademicProgressRecord_version12.data.remote.RetrofitClient
import com.example.AcademicProgressRecord_version12.data.remote.SubjectApi
import com.example.AcademicProgressRecord_version12.data.remote.toDomain
import com.example.AcademicProgressRecord_version12.domain.SubjectRepository
import kotlinx.coroutines.CancellationException

class SubjectRepositoryImpl(
    private val api: SubjectApi = RetrofitClient.api
) : SubjectRepository {
    override suspend fun getSubjects(): List<Subject> {
        return try {
            api.getSubjects().map { it.toDomain() }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("SubjectRepository", "Не вдалося завантажити предмети", e)
            emptyList()
        }
    }
}