package com.rvcode.atomberg_app.models

import com.google.gson.annotations.SerializedName

data class SendCommandRequest(
    @SerializedName("device_id")
    val deviceId: String,
    val command: Command
)

