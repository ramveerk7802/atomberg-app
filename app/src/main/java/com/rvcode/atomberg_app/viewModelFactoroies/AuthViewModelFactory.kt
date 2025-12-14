package com.rvcode.atomberg_app.viewModelFactoroies

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rvcode.atomberg_app.localStorage.DataStoreManager
import com.rvcode.atomberg_app.repositories.AuthRepository
import com.rvcode.atomberg_app.viewmodels.AuthViewModel

class AuthViewModelFactory(application: Application) : ViewModelProvider.Factory{
    private val appContext = application.applicationContext
    private val dataStoreManager = DataStoreManager(appContext)
    private val repository = AuthRepository()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(dataStoreManager = dataStoreManager,repository  = repository)  as T
    }

}
