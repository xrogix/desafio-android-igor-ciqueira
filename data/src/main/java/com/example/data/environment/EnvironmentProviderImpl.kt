package com.example.data.environment

import android.content.Context
import com.example.data.R

class EnvironmentProviderImpl(private val context: Context) :
    EnvironmentProvider {
    override fun getUrlService() = context.getString(R.string.url_service)

    override fun getPublicKey() = context.getString(R.string.public_key)

    override fun getPrivateKey() = context.getString(R.string.private_key)
}