package com.example.AcademicProgressRecord_version12.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subjects ORDER BY id")
    fun getAll(): Flow<List<SubjectEntity>>
    @Query("SELECT * FROM subjects WHERE id = :id")
    fun getById(id: Int): Flow<SubjectEntity?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SubjectEntity>)
    @Query("DELETE FROM subjects")
    suspend fun deleteAll()
}

@Dao
interface ComponentDao {
    @Query("SELECT * FROM components WHERE subjectId = :subjectId ORDER BY id")
    fun getBySubject(subjectId: Int): Flow<List<ComponentEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ComponentEntity>)
    @Query("DELETE FROM components")
    suspend fun deleteAll()
}