package com.rvcode.atomberg_app.repositories

import android.app.Application
import android.content.Context
import com.rvcode.atomberg_app.localStorage.DataStoreManager
import com.rvcode.atomberg_app.models.TokenResponse
import com.rvcode.atomberg_app.networks.AtombergAuthApi
import com.rvcode.atomberg_app.networks.RetrofitClient

class AuthRepository() {

private val api = RetrofitClient.authApi
    suspend fun fetchAccessToken(apikey: String,refreshToken: String) : TokenResponse?{
        val response  = api.getAccessToken(apiKey = apikey, authorization = "Bearer $refreshToken")
        if (response.isSuccessful && response.body()!=null){
            return response.body()
        }
        else
            return null
    }


}