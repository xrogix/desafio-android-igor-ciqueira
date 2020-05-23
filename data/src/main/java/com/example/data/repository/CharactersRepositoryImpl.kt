package com.example.data.repository

import com.example.data.environment.Environment
import com.example.data.services.ServiceProvider
import com.example.domain.model.Characters
import com.example.domain.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val serviceProvider: ServiceProvider,
    private val environment: Environment
) : CharactersRepository {

    override fun listCharacters(): List<Characters> {
        val ts = System.currentTimeMillis().toString()
        val hash = ts + environment.privateKey + environment.publicKey

//        return serviceProvider.getService().listCharacters(
//            ts,
//            environment.publicKey,
//            hash.toMD5()
//        )
        return listOf()
    }

}