package com.rvcode.atomberg_app.networks

import com.rvcode.atomberg_app.models.Device
import com.rvcode.atomberg_app.models.DeviceResponse
import retrofit2.Response
import retrofit2.http.GET

interface AtombergApi {
    @GET("v1/get_list_of_devices")
    suspend fun getListDevice(): Response<DeviceResponse>
}