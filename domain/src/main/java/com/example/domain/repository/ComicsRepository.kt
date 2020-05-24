package com.example.domain.repository

import com.example.domain.model.Comics

interface ComicsRepository {
    suspend fun getComicsByCharacter(id: String) : List<Comics>
}