package com.example.domain.repository

import com.example.domain.model.Characters

interface CharactersRepository {
    fun listCharacters(): List<Characters>
}