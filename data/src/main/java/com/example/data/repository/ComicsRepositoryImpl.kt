package com.example.data.repository

import com.example.data.services.ServiceProvider
import com.example.domain.model.Comics
import com.example.domain.repository.ComicsRepository

class ComicsRepositoryImpl(
    private val serviceProvider: ServiceProvider
): ComicsRepository {
    override suspend fun getComicsByCharacter(id: String): List<Comics> {
        return serviceProvider.getService().listComics(id)
    }
}