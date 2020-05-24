package com.example.desafioandroidigorciqueira.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.domain.model.Characters
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(
    private val charactersRepository: CharactersRepository
): ViewModel() {

    val characterList: LiveData<List<Characters>> = liveData(Dispatchers.IO) {
        val list = charactersRepository.listCharacters().data.results
        emit(list)
    }

}