package com.dicoding.asclepius.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryAnalyzeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyAnalyze: HistoryAnalyze)

    @Query("SELECT * FROM HistoryAnalyze")
    fun getAllHistory(): LiveData<List<HistoryAnalyze>>

    @Query("DELETE FROM HistoryAnalyze WHERE id = :id")
    suspend fun deleteById(id: Int)
}
