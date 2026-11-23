package com.example.AcademicProgressRecord_version12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.AcademicProgressRecord_version12.data.SubjectRepositoryImpl
import com.example.AcademicProgressRecord_version12.domain.GetSubjectsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ListUiState(
    val subjects: List<Subject> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = false
) {
    val visibleSubjects: List<Subject>
        get() = subjects.filter { it.name.contains(query, ignoreCase = true) }
}
class ListViewModel(
    private val getSubjects: GetSubjectsUseCase = GetSubjectsUseCase(SubjectRepositoryImpl())
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState(isLoading = true))
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()
    init {
        loadSubjects()
    }
    private fun loadSubjects() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val subjects = getSubjects()
            _uiState.update { it.copy(subjects = subjects, isLoading = false) }
        }
    }
    fun onQueryChange(newQuery: String) {
        _uiState.update { current -> current.copy(query = newQuery) }
    }
}