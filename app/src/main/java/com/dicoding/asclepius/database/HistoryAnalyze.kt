package com.dicoding.asclepius.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "HistoryAnalyze")
data class HistoryAnalyze(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUri: String,
    val classificationLabel: String,
    val confidenceScore: Float
) : Parcelable