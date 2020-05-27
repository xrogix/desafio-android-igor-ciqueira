package com.example.data.repository

import com.example.data.environment.Environment
import com.example.data.extension.toMD5
import com.example.data.services.ServiceProvider
import com.example.domain.model.Characters
import com.example.domain.model.Wrapper
import com.example.domain.repository.CharactersRepository
import com.example.domain.repository.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CharactersRepositoryImpl(
    private val serviceProvider: ServiceProvider,
    private val environment: Environment,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharactersRepository {

    override suspend fun listCharacters(offset: Int): ResultWrapper<Wrapper<Characters>> {
        val ts = System.currentTimeMillis().toString()
        val hash = ts + environment.privateKey + environment.publicKey

        return safeApiCall(dispatcher) {
            serviceProvider.getService().listCharacters(
                ts,
                environment.publicKey,
                hash.toMD5(),
                "20",
                offset.toString()
            )
        }
    }

}