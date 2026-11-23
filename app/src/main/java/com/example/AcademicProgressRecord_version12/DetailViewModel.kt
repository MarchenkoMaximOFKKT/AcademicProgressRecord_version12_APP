package com.example.AcademicProgressRecord_version12

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.AcademicProgressRecord_version12.domain.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class DetailUiState(
    val subject: Subject? = null,
    val components: List<GradeComponent> = emptyList(),
    val isLoaded: Boolean = false
)
@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: SubjectRepository
) : ViewModel() {
    private val subjectId: Int = checkNotNull(savedStateHandle["itemId"])

    val uiState: StateFlow<DetailUiState> = combine(
        repository.observeSubject(subjectId),
        repository.observeComponents(subjectId)
    ) { subject, components ->
        DetailUiState(subject, components, isLoaded = true)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DetailUiState())
}