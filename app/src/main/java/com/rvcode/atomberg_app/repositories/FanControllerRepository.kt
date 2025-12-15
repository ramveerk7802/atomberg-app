package com.rvcode.atomberg_app.repositories

import android.util.Log
import com.rvcode.atomberg_app.models.Command
import com.rvcode.atomberg_app.models.SendCommandRequest
import com.rvcode.atomberg_app.models.device_state.DeviceState
import com.rvcode.atomberg_app.networks.AtombergApi

class FanControllerRepository(private val api : AtombergApi) {




    suspend fun getCurrentState():List<DeviceState>?{
        val response = api.getDeviceState()
        return if(response.isSuccessful && response.body()!=null){
             response.body()?.message?.device_state
        }
        else
             null
    }



    suspend fun setPower(deviceId:String,isPower: Boolean){

        val request = SendCommandRequest(
            deviceId = deviceId,
            command = Command(power = isPower)
        )

        val response = api.sendCommand(request)

        if (response.isSuccessful) {
            val body = response.body()

            if (body?.status == "Success") {
                Log.d("CONTROLLER", "Power command sent: $isPower")
            } else {
                throw Exception(body?.message ?: "Command failed")
            }

        } else {
            throw Exception("HTTP Error: ${response.code()} ")
        }
    }

    suspend fun setSpeed(deviceId:String,speed: Int){

        val request = SendCommandRequest(
            deviceId = deviceId,
            command = Command(speed = speed)
        )
        api.sendCommand(request)
    }

    suspend fun setLight(deviceId:String,isLight: Boolean){

        val request = SendCommandRequest(
            deviceId = deviceId,
            command = Command(led = isLight)
        )
        api.sendCommand(request)
    }

    suspend fun setTimer(deviceId: String, time:Int){
        val request = SendCommandRequest(
            deviceId = deviceId,
            command = Command(
                timer = time
            )
        )
        api.sendCommand(request)
    }


    suspend fun setSleep(deviceId: String,isSleep: Boolean){
        val request = SendCommandRequest(
            deviceId = deviceId,
            command = Command(sleep = isSleep)
        )
        api.sendCommand(request)
    }
}