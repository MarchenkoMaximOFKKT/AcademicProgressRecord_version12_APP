package com.example.AcademicProgressRecord_version12

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.AcademicProgressRecord_version12.data.SubjectRepositoryImpl
import com.example.AcademicProgressRecord_version12.data.local.AppDatabase
import com.example.AcademicProgressRecord_version12.data.remote.RetrofitClient
import com.example.AcademicProgressRecord_version12.domain.SubjectRepository

class AppContainer(context: Context) {
    private val db: AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, "app.db")
        .fallbackToDestructiveMigration(true)
        .build()
    val subjectRepository: SubjectRepository = SubjectRepositoryImpl(db, RetrofitClient.api)
}

class AcademicApp : Application() {
    val container: AppContainer by lazy { AppContainer(this) }
}