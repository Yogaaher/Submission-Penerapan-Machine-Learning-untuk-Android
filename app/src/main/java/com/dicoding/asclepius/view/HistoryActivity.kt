package com.dicoding.asclepius.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.database.HistoryAdapter
import com.dicoding.asclepius.database.HistoryAnalyze
import com.dicoding.asclepius.database.HistoryAnalyzeRoom
import com.dicoding.asclepius.repository.HistoryAnalyzeRepository

class HistoryActivity : AppCompatActivity() {
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val toolbar: Toolbar = findViewById(R.id.toolbarHistory)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        historyAdapter = HistoryAdapter(mutableListOf()) { historyAnalyze ->
            deleteHistory(historyAnalyze)
        }
        recyclerView.adapter = historyAdapter

        val historyAnalyzeDao = HistoryAnalyzeRoom.getDatabase(application).historyAnalyzeDao()
        val repository = HistoryAnalyzeRepository(historyAnalyzeDao)
        val factory = ViewModelFactory(repository)
        historyViewModel = ViewModelProvider(this, factory)[HistoryViewModel::class.java]
        historyViewModel.historyList.observe(this) { historyList ->
            historyAdapter.updateHistoryList(historyList)
        }
    }

    private fun deleteHistory(historyAnalyze: HistoryAnalyze) {
        historyViewModel.deleteHistory(historyAnalyze.id)
    }
}
