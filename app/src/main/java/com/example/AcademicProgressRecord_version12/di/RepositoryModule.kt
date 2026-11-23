package com.example.AcademicProgressRecord_version12.di

import com.example.AcademicProgressRecord_version12.data.SubjectRepositoryImpl
import com.example.AcademicProgressRecord_version12.domain.SubjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSubjectRepository(impl: SubjectRepositoryImpl): SubjectRepository
}