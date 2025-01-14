package com.dicoding.asclepius.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryAnalyze::class], version = 1, exportSchema = false)
abstract class HistoryAnalyzeRoom : RoomDatabase() {
    abstract fun historyAnalyzeDao(): HistoryAnalyzeDao
    companion object {
        @Volatile
        private var INSTANCE: HistoryAnalyzeRoom? = null

        fun getDatabase(context: Context): HistoryAnalyzeRoom {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryAnalyzeRoom::class.java,
                    "history_analyze_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
