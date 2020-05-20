package com.example.data.repository

import com.example.domain.Comics
import retrofit2.Call

interface ComicsRepository {
    fun getComicsByCharacter(id: String) : Call<List<Comics>>
}