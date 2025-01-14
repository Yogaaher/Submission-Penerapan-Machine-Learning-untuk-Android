package com.dicoding.asclepius.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.database.HistoryAnalyze
import com.dicoding.asclepius.repository.HistoryAnalyzeRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryAnalyzeRepository) : ViewModel() {
    val historyList: LiveData<List<HistoryAnalyze>> = repository.getAllHistory()

    fun deleteHistory(id: Int) {
        viewModelScope.launch {
            repository.deleteById(id)
        }
    }
}


