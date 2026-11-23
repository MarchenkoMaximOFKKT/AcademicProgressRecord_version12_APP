package com.example.AcademicProgressRecord_version12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.AcademicProgressRecord_version12.domain.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ListUiState(
    val subjects: List<Subject> = emptyList(),
    val query: String = "",
    val isRefreshing: Boolean = false,
    val syncError: String? = null
) {
    val visibleSubjects: List<Subject>
        get() = subjects.filter { it.name.contains(query, ignoreCase = true) }
}
class ListViewModel(
    private val repository: SubjectRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()
    init {
        observeCache()
        refresh()
    }
    private fun observeCache() {
        viewModelScope.launch {
            repository.observeSubjects().collect { list ->
                _uiState.update { it.copy(subjects = list) }
            }
        }
    }
    fun refresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            when (val result = repository.refresh()) {
                is Result.Success -> _uiState.update { it.copy(isRefreshing = false, syncError = null) }
                is Result.Error -> _uiState.update { it.copy(isRefreshing = false, syncError = result.message) }
            }
        }
    }
    fun onSyncErrorShown() {
        _uiState.update { it.copy(syncError = null) }
    }
    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }
}