package com.example.AcademicProgressRecord_version12

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { ListViewModel(academicApp().container.subjectRepository) }
        initializer {
            DetailViewModel(createSavedStateHandle(), academicApp().container.subjectRepository)
        }
    }
}

private fun CreationExtras.academicApp(): AcademicApp =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AcademicApp