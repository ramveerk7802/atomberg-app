package com.rvcode.atomberg_app.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rvcode.atomberg_app.models.device_state.DeviceState
import com.rvcode.atomberg_app.repositories.FanControllerRepository
import kotlinx.coroutines.launch

class FanControllerViewModel(private val repository: FanControllerRepository): ViewModel() {

    private val _deviceStateList = MutableLiveData<List<DeviceState>>()

    val deviceStateList : LiveData<List<DeviceState>> get() = _deviceStateList

    init {
        viewModelScope.launch {
            val result = repository.getCurrentState()
            result?.let {
                _deviceStateList.value = it
            }
        }

    }



    fun setPower(deviceId: String, isPower: Boolean) {
        viewModelScope.launch {
            try {
                repository.setPower(deviceId, isPower)
                repository.setSpeed(deviceId, speed = 3)
            } catch (e: Exception) {
                // Handle error safely
                Log.d("FANVIEWMODEL", "MESSAGE : ${e.message} ")
            }
        }
    }

    fun setLight(deviceId: String, isLight: Boolean) {
        viewModelScope.launch {
            try {
                repository.setLight(deviceId, isLight)
            } catch (e: Exception) {
                // Handle error safely
                Log.d("FANVIEWMODEL", "MESSAGE : ${e.message}  and ${e.localizedMessage}")
            }
        }
    }

    fun enableSleepMode(deviceId: String, isSleep: Boolean){
        viewModelScope.launch {
            repository.setSleep(deviceId = deviceId, isSleep = isSleep)
        }
    }

    fun setTimer(deviceId: String, time: Int){
        viewModelScope.launch {
            repository.setTimer(deviceId = deviceId, time = time)
        }
    }

    fun setAbsoluteSpeed(deviceId: String, speed: Int){
        viewModelScope.launch {
            repository.setSpeed(deviceId = deviceId, speed = speed)
        }
    }

}