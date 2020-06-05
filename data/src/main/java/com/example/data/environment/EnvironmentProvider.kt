package com.example.data.environment

interface EnvironmentProvider {
    fun getUrlService(): String

    fun getPublicKey(): String

    fun getPrivateKey(): String
}