package com.example.desafioandroidigorciqueira.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Characters
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val charactersRepository: CharactersRepository
): ViewModel() {

    private val _characterList = MutableLiveData<MutableList<Characters>>()
    val characterList: LiveData<MutableList<Characters>>
        get() = _characterList

    private val _lastCharacterList = MutableLiveData<MutableList<Characters>>()
    val lastCharacterList: LiveData<MutableList<Characters>>
        get() = _lastCharacterList

    private val list = mutableListOf<Characters>()


    fun loadMoreCharacters(offset: Int = 0) {
        CoroutineScope(Dispatchers.Main).launch {
            val newRequisittion = withContext(Dispatchers.Default) {
                charactersRepository.listCharacters(offset).data.results
            }
            list.addAll(newRequisittion)
            _lastCharacterList.value = newRequisittion
            _characterList.value = list
        }
    }

    fun getEmptyList() = mutableListOf<Characters?>()
}