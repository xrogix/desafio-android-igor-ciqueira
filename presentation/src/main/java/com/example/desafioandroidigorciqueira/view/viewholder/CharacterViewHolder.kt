package com.example.desafioandroidigorciqueira.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.services.ImageProvider
import com.example.desafioandroidigorciqueira.R
import com.example.domain.model.Characters

internal class CharacterViewHolder(private val imageProvider: ImageProvider, itemView: View): RecyclerView.ViewHolder(itemView) {
    private val ivCharacterImage = itemView.findViewById<AppCompatImageView>(R.id.iv_character_image)
    private val tvCharacterName = itemView.findViewById<AppCompatTextView>(R.id.tv_character_name)

    fun bind(character: Characters) {
        imageProvider.renderImage(character.thumbnail.path, ivCharacterImage)
        tvCharacterName.text = character.name
    }
}