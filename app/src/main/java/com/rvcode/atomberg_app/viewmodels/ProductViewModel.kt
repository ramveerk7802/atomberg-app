package com.rvcode.atomberg_app.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcode.atomberg_app.models.Device
import com.rvcode.atomberg_app.repositories.AuthRepository
import com.rvcode.atomberg_app.repositories.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _devices = MutableLiveData<List<Device>>()
    val devices : LiveData<List<Device>> get() = _devices
    init {
        viewModelScope.launch {
            val result = repository.getDeviceList()
            if(result!=null){
                _devices.value=result
                Log.d("NETWORK", result[0].deviceId)
            }
        }
    }
}