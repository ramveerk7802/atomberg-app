package com.rvcode.atomberg_app.localStorage


import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DataStoreTokenProvider (private val dataStoreManager: DataStoreManager) : TokenProvider {
    override fun getApiKey(): String? = runBlocking {
            dataStoreManager.apiKey.first()
        }


    override fun getAccessToken(): String?  = runBlocking {
        dataStoreManager.accessToken.first()
    }
}