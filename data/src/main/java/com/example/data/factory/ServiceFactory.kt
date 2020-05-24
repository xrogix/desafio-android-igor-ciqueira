package com.example.data.factory

import com.example.data.environment.Environment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceFactory(
    private val environment: Environment
) {

    fun <T> create(serviceType: Class<T>): T {
        return create(
            serviceType,
            environment.baseUrl
        )
    }

    private fun apiAdapter(baseUrl: String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun <T> create(
        serviceType: Class<T>,
        baseUrl: String
    ) : T {
        val retrofit = apiAdapter(baseUrl)
        return retrofit.create(serviceType)
    }

}