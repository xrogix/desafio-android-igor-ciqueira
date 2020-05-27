package com.example.domain.repository

import com.example.domain.model.Characters
import com.example.domain.model.Wrapper

interface CharactersRepository {
    suspend fun listCharacters(offset: Int = 0): ResultWrapper<Wrapper<Characters>>
}