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

    //all character list
    private val list = mutableListOf<Characters>()
    private val _characterList = MutableLiveData<MutableList<Characters>>()
    val characterList: LiveData<MutableList<Characters>>
        get() = _characterList


    //control 20 last requisition characters
    private val _lastCharacterList = MutableLiveData<MutableList<Characters>>()
    val lastCharacterList: LiveData<MutableList<Characters>>
        get() = _lastCharacterList

    fun loadMoreCharacters(offset: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            //begin new requisition
            val newRequisittion = withContext(Dispatchers.Default) {
                charactersRepository.listCharacters(offset).data.results
            }

            //add result do character list
            list.addAll(newRequisittion)
            _characterList.value = list

            //add last request for control recyclerview pagination
            _lastCharacterList.value = newRequisittion
        }
    }

    fun getEmptyList() = mutableListOf<Characters?>()
}