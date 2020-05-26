package com.example.desafioandroidigorciqueira.view.viewholder

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidigorciqueira.R
import com.example.desafioandroidigorciqueira.extensions.show

internal class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_item_load)
    fun bind() {
        progressBar.show()
    }
}