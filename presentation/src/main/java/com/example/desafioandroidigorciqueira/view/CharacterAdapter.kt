package com.example.desafioandroidigorciqueira.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidigorciqueira.R
import com.example.domain.model.Characters

class CharacterAdapter(
    private val characterList: List<Characters>
): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val tvCharacterName = itemView.findViewById<AppCompatTextView>(R.id.tv_character_name)

        fun bind(character: Characters) {
            tvCharacterName.text = character.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = characterList[position]
        holder.bind(item)
    }

    override fun getItemCount() = characterList.size
}