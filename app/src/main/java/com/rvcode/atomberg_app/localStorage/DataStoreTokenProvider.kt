package com.rvcode.atomberg_app.localStorage

import kotlinx.coroutines.runBlocking

class DataStoreTokenProvider(
    private val dataStoreManager: DataStoreManager
) : TokenProvider {

    override fun getApiKey(): String? = runBlocking {
        dataStoreManager.getApiKeyOnce()
    }

    override fun getAccessToken(): String? = runBlocking {
        dataStoreManager.getAccessTokenOnce()
    }
}