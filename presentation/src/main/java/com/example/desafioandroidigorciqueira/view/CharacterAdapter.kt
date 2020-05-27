package com.example.desafioandroidigorciqueira.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.services.ImageProvider
import com.example.desafioandroidigorciqueira.R
import com.example.desafioandroidigorciqueira.extensions.listen
import com.example.desafioandroidigorciqueira.view.viewholder.CharacterViewHolder
import com.example.desafioandroidigorciqueira.view.viewholder.LoadingViewHolder
import com.example.domain.model.Characters

class CharacterAdapter(
    private val imageProvider: ImageProvider,
    private var characterList: MutableList<Characters?>,
    private val onSelectedCharacter: (position: Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if(characterList[position] == null)
            VIEW_TYPE_LOADING
        else
            VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_ITEM) {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.character_item, parent, false)
                CharacterViewHolder(imageProvider, view).listen { position, _ ->
                    onSelectedCharacter.invoke(position)
                }
            } else {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loading, parent, false)
                LoadingViewHolder(view)
            }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CharacterViewHolder)
            characterList[position]?.let {
                holder.bind(it)
            }
        else if (holder is LoadingViewHolder)
            holder.bind()
    }

    override fun getItemCount() = characterList.size

    fun updateData(list: MutableList<Characters>) {
        characterList.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(character: Characters?) {
        characterList.add(character)
        notifyItemInserted(characterList.size - 1)
    }

    fun removeItem() {
        characterList.removeAt(characterList.size - 1)
        notifyItemRemoved(characterList.size)
    }

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }
}