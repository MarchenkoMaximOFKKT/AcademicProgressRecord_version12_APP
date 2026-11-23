package com.example.AcademicProgressRecord_version12.data

import android.util.Log
import androidx.room.withTransaction
import com.example.AcademicProgressRecord_version12.GradeComponent
import com.example.AcademicProgressRecord_version12.Result
import com.example.AcademicProgressRecord_version12.Subject
import com.example.AcademicProgressRecord_version12.data.local.AppDatabase
import com.example.AcademicProgressRecord_version12.data.local.ComponentDao
import com.example.AcademicProgressRecord_version12.data.local.SubjectDao
import com.example.AcademicProgressRecord_version12.data.remote.SubjectApi
import com.example.AcademicProgressRecord_version12.domain.SubjectRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val subjectDao: SubjectDao,
    private val componentDao: ComponentDao,
    private val api: SubjectApi
) : SubjectRepository {

    override fun observeSubjects(): Flow<List<Subject>> =
        subjectDao.getAll().map { list -> list.map { it.toDomain() } }

    override fun observeSubject(id: Int): Flow<Subject?> =
        subjectDao.getById(id).map { it?.toDomain() }

    override fun observeComponents(subjectId: Int): Flow<List<GradeComponent>> =
        componentDao.getBySubject(subjectId).map { list -> list.map { it.toDomain() } }

    override suspend fun refresh(): Result<Unit> = try {
        val subjects = api.getSubjects().map { it.toEntity() }
        val components = api.getComponents().map { it.toEntity() }
        db.withTransaction {
            subjectDao.deleteAll()
            subjectDao.insertAll(subjects)
            componentDao.deleteAll()
            componentDao.insertAll(components)
        }
        Result.Success(Unit)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Log.e("SubjectRepository", "Помилка синхронізації", e)
        Result.Error(e.message ?: "Помилка синхронізації")
    }
}