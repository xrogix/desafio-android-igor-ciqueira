package com.example.data.factory

import com.example.data.environment.EnvironmentProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceFactory(
    private val environment: EnvironmentProvider
) {

    fun <T> create(serviceType: Class<T>): T {
        return create(
            serviceType,
            environment.getUrlService()
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