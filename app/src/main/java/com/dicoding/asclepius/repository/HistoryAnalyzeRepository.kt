package com.dicoding.asclepius.repository

import androidx.lifecycle.LiveData
import com.dicoding.asclepius.database.HistoryAnalyze
import com.dicoding.asclepius.database.HistoryAnalyzeDao

class HistoryAnalyzeRepository(private val historyAnalyzeDao: HistoryAnalyzeDao) {

    fun getAllHistory(): LiveData<List<HistoryAnalyze>> {
        return historyAnalyzeDao.getAllHistory()
    }

    suspend fun insert(historyAnalyze: HistoryAnalyze) {
        historyAnalyzeDao.insert(historyAnalyze)
    }

    suspend fun deleteById(id: Int) {
        historyAnalyzeDao.deleteById(id)
    }
}
