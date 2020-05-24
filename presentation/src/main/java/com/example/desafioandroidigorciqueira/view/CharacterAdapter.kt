package com.example.desafioandroidigorciqueira.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.services.ImageProvider
import com.example.desafioandroidigorciqueira.R
import com.example.domain.model.Characters

class CharacterAdapter(
    private val imageProvider: ImageProvider,
    private val characterList: List<Characters>
): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(private val imageProvider: ImageProvider, itemView: View): RecyclerView.ViewHolder(itemView) {
        private val ivCharacterImage = itemView.findViewById<AppCompatImageView>(R.id.iv_character_image)
        private val tvCharacterName = itemView.findViewById<AppCompatTextView>(R.id.tv_character_name)

        fun bind(character: Characters) {
            imageProvider.renderImage(character.thumbnail.path, ivCharacterImage)
            tvCharacterName.text = character.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)
        return ViewHolder(imageProvider, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = characterList[position]
        holder.bind(item)
    }

    override fun getItemCount() = characterList.size
}