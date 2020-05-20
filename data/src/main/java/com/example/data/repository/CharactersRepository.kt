package com.example.data.repository

import com.example.domain.Characters
import retrofit2.Call

interface CharactersRepository {
    fun listCharacters(): Call<List<Characters>>
}