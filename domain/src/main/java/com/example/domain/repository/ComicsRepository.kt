package com.example.domain.repository

import com.example.domain.model.Comics

interface ComicsRepository {
    fun getComicsByCharacter(id: String) : List<Comics>
}