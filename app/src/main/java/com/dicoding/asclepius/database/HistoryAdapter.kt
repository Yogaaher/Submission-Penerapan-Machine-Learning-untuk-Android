package com.dicoding.asclepius.database

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R

class HistoryAdapter(
    private val historyList: MutableList<HistoryAnalyze>,
    private val onDeleteClick: (HistoryAnalyze) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val labelTextView: TextView = itemView.findViewById(R.id.labelTextView)
        val confidenceTextView: TextView = itemView.findViewById(R.id.confidenceTextView)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyAnalyze = historyList[position]

        holder.labelTextView.text = historyAnalyze.classificationLabel
        holder.confidenceTextView.text = String.format("%.0f%%", historyAnalyze.confidenceScore * 100)
        Glide.with(holder.itemView.context)
            .load(historyAnalyze.imageUri)
            .placeholder(R.drawable.ic_place_holder)
            .error(R.drawable.error_image)
            .into(holder.imageView)

        holder.deleteButton.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Konfirmasi Penghapusan")
                .setMessage("Apakah Anda yakin ingin menghapus item ini?")
                .setPositiveButton("Ya") { dialog, _ ->
                    onDeleteClick(historyAnalyze)
                    dialog.dismiss()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateHistoryList(newHistoryList: List<HistoryAnalyze>) {
        historyList.clear()
        historyList.addAll(newHistoryList)
        notifyDataSetChanged()
    }
}
