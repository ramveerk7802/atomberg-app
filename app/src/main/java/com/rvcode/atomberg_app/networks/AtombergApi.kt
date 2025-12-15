package com.rvcode.atomberg_app.networks

import com.rvcode.atomberg_app.models.Device
import com.rvcode.atomberg_app.models.DeviceResponse
import com.rvcode.atomberg_app.models.SendCommandRequest
import com.rvcode.atomberg_app.models.SendCommandResponse
import com.rvcode.atomberg_app.models.device_state.DeviceStateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AtombergApi {

    @GET("v1/get_list_of_devices")
    suspend fun getListDevice(): Response<DeviceResponse>

    @POST("v1/send_command")
    suspend fun sendCommand(
        @Body request: SendCommandRequest
    ): Response<SendCommandResponse>

    @GET("v1/get_device_state?device_id=all")
    suspend fun getDeviceState(): Response<DeviceStateResponse>
}
