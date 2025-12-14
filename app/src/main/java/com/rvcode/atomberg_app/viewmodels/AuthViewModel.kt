package com.rvcode.atomberg_app.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcode.atomberg_app.localStorage.DataStoreManager
import com.rvcode.atomberg_app.repositories.AuthRepository
import kotlinx.coroutines.launch


class AuthViewModel (private val dataStoreManager: DataStoreManager,
                     private val repository: AuthRepository): ViewModel() {

    fun fetchAccessToken(apikey: String,refreshToken: String,onSuccess:()-> Unit){
        viewModelScope.launch {
            val response = repository.fetchAccessToken(apikey = apikey, refreshToken = refreshToken)
            if(response!=null) {
                dataStoreManager.saveApiKey(apiKey = apikey, response.message.access_token)
                onSuccess()
            }
            else
                Log.d("TOKEN","Response is null")
        }
    }
}