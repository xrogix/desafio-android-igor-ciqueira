package com.example.data.repository

import com.example.data.services.ServiceProvider
import com.example.domain.Comics
import retrofit2.Call

class ComicsRepositoryImpl(
    private val serviceProvider: ServiceProvider
): ComicsRepository {
    override fun getComicsByCharacter(id: String): Call<List<Comics>> {
        return serviceProvider.getService().listComics(id)
    }
}