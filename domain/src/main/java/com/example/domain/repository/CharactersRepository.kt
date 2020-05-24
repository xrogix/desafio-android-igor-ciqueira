package com.example.domain.repository

import com.example.domain.model.Wrapper

interface CharactersRepository {
    suspend fun listCharacters(): Wrapper
}