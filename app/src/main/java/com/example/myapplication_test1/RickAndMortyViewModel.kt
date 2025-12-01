package com.example.myapplication_test1

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    val items = repository.getCombinedData()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.value = ListUiState.Loading

                // Проверяем, нужно ли загружать данные
                if (repository.shouldRefreshData()) {
                    repository.refreshData()
                }

                _uiState.value = ListUiState.Success
            } catch (e: Exception) {
                _uiState.value = ListUiState.Error(
                    message = e.message ?: "Неизвестная ошибка"
                )
            }
        }
    }

    fun retry() {
        loadData()
    }
}

sealed class ListUiState {
    object Loading : ListUiState()
    object Success : ListUiState()
    data class Error(val message: String) : ListUiState()
}