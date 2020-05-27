package com.example.data.repository

import com.example.data.environment.Environment
import com.example.data.extension.toMD5
import com.example.data.services.ServiceProvider
import com.example.domain.model.Comics
import com.example.domain.model.Wrapper
import com.example.domain.repository.ComicsRepository

class ComicsRepositoryImpl(
    private val serviceProvider: ServiceProvider,
    private val environment: Environment
): ComicsRepository {
    override suspend fun getComicsByCharacter(id: String): Wrapper<Comics> {
        val ts = System.currentTimeMillis().toString()
        val hash = ts + environment.privateKey + environment.publicKey

        return serviceProvider.getService().listComics(
            id,
            ts,
            environment.publicKey,
            hash.toMD5()
        )
    }
}