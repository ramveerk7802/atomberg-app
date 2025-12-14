package com.rvcode.atomberg_app.viewModelFactoroies

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rvcode.atomberg_app.localStorage.DataStoreManager
import com.rvcode.atomberg_app.networks.RetrofitClient
import com.rvcode.atomberg_app.repositories.AuthRepository
import com.rvcode.atomberg_app.viewmodels.AuthViewModel

class AuthViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 1. Check if the requested ViewModel is AuthViewModel
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {

            // 2. Create Dependencies
            val dataStoreManager = DataStoreManager(application)

            // Get the API service from your RetrofitClient singleton
            val authApi = RetrofitClient.authApi

            // Inject the API into the Repository
            val repository = AuthRepository(authApi)

            // 3. Return the ViewModel
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(dataStoreManager, repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
