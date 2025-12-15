package com.rvcode.atomberg_app.viewModelFactoroies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rvcode.atomberg_app.networks.RetrofitClient
import com.rvcode.atomberg_app.repositories.FanControllerRepository
import com.rvcode.atomberg_app.viewmodels.FanControllerViewModel

class FanControllerViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = FanControllerRepository(RetrofitClient.atombergApi)
        return FanControllerViewModel(repository) as T
    }
}