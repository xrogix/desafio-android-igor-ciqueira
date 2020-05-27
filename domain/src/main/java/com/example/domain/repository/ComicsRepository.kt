package com.example.domain.repository

import com.example.domain.model.Comics
import com.example.domain.model.Wrapper

interface ComicsRepository {
    suspend fun getComicsByCharacter(id: String) : Wrapper<Comics>
}