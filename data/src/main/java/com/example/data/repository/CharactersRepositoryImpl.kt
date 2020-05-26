package com.example.data.repository

import com.example.data.environment.Environment
import com.example.data.extension.toMD5
import com.example.data.services.ServiceProvider
import com.example.domain.model.Wrapper
import com.example.domain.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val serviceProvider: ServiceProvider,
    private val environment: Environment
) : CharactersRepository {

    override suspend fun listCharacters(offset: Int): Wrapper {
        val ts = System.currentTimeMillis().toString()
        val hash = ts + environment.privateKey + environment.publicKey

        return serviceProvider.getService().listCharacters(
            ts,
            environment.publicKey,
            hash.toMD5(),
            "20",
            offset.toString()
        )
    }

}