package com.example.desafioandroidigorciqueira.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Characters
import com.example.domain.model.Comics
import com.example.domain.repository.CharactersRepository
import com.example.domain.repository.ComicsRepository
import com.example.domain.repository.ResultWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val charactersRepository: CharactersRepository,
    private val comicsRepository: ComicsRepository
): ViewModel() {

    //all character list
    private val list = mutableListOf<Characters?>()
    private val _characterList = MutableLiveData<MutableList<Characters?>>()
    val characterList: LiveData<MutableList<Characters?>>
        get() = _characterList

    private val _characterDetail = MutableLiveData<Characters>()
    val charactersDetail : LiveData<Characters>
        get() = _characterDetail

    private val _comicsList = MutableLiveData<MutableList<Comics>>()
    val comicsList : LiveData<MutableList<Comics>>
        get() = _comicsList

    private val _networkError = MutableLiveData<Boolean>()
    val networkError : LiveData<Boolean>
        get() = _networkError

    //control 20 last requisition characters
    private val _lastCharacterList = MutableLiveData<MutableList<Characters>>()
    val lastCharacterList: LiveData<MutableList<Characters>>
        get() = _lastCharacterList

    fun loadMoreCharacters(offset: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            //begin new requisition
            when(val characterList = charactersRepository.listCharacters(offset)) {
                is ResultWrapper.NetworkError -> showNetworkError()
                is ResultWrapper.Success ->  {
                    val newRequisition = characterList.value.data.results

                    //add result do character list
                    list.addAll(newRequisition)
                    _characterList.value = list

                    //add last request for control recyclerview pagination
                    _lastCharacterList.value = newRequisition
                }
            }
        }
    }

    private fun showNetworkError() {
        _networkError.value = true
    }

    fun findExpensiveComic() {
        CoroutineScope(Dispatchers.Main).launch {
            _comicsList.value = withContext(Dispatchers.Default) {
                _characterDetail.value?.let {
                    comicsRepository.getComicsByCharacter(it.id.toString()).data.results
                }
            }
        }
    }

    fun onSelectedCharacter(position: Int) {
        _characterList.value?.also {
            _characterDetail.value = it[position]
        }
    }

    fun getCharacterList(): MutableList<Characters?> {
        return _characterList.value ?: mutableListOf<Characters?>()
    }
}