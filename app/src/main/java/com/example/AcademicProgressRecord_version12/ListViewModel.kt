package com.example.AcademicProgressRecord_version12

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ListUiState(
    val subjects: List<Subject> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = false
) {
    val visibleSubjects: List<Subject>
        get() = subjects.filter { it.name.contains(query, ignoreCase = true) }
}
class ListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState(subjects = sampleSubjects))
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()
    fun onQueryChange(newQuery: String) {
        _uiState.update { current -> current.copy(query = newQuery) }
    }
}