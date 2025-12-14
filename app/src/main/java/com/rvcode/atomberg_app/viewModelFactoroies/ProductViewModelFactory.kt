package com.rvcode.atomberg_app.viewModelFactoroies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rvcode.atomberg_app.networks.RetrofitClient
import com.rvcode.atomberg_app.repositories.ProductRepository
import com.rvcode.atomberg_app.viewmodels.ProductViewModel

class ProductViewModelFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ProductRepository(api = RetrofitClient.atombergApi)
        return ProductViewModel(repository) as T
    }
}