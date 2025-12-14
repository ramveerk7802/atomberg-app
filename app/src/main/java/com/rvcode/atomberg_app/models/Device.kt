package com.rvcode.atomberg_app.models

import com.google.gson.annotations.SerializedName

data class Device(
    val metadata: DeviceMetaData,
    @SerializedName("device_id")
    val deviceId: String,
    val color: String,
    val series: String,
    val model: String,
    val room: String,
    val name: String
)

