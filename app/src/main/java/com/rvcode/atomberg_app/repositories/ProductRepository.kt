package com.rvcode.atomberg_app.repositories

import android.util.Log
import com.rvcode.atomberg_app.models.Device
import com.rvcode.atomberg_app.networks.AtombergApi

class ProductRepository(private val api: AtombergApi) {

    suspend fun getDeviceList(): List<Device>?{
        try {
            val response = api.getListDevice()
            if(response.isSuccessful && response.body()!=null){
                return response.body()?.message?.devices_list
            }else
                return null
        }catch (e: Exception){
            Log.d("NETWORK","EXCEPTION ${e.message}   local message ${e.localizedMessage}" )
            return null
        }

    }
}