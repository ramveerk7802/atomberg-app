package com.rvcode.atomberg_app.models.device_state

import com.google.gson.annotations.SerializedName

data class DeviceState(
    @SerializedName("device_id")
    val deviceId: String,
    val power: Boolean,
    @SerializedName("last_recorded_speed")
    val lastRecordedSpeed: Int,
    @SerializedName("sleep_mode")
    val sleepMode: Boolean,
    val led: Boolean,
    @SerializedName("is_online")
    val isOnline: Boolean,
    @SerializedName("timer_hours")
    val timerHours: Int,
    @SerializedName("timer_time_elapsed_mins")
    val timerTimeElapsedMins: Int,
    @SerializedName("ts_epoch_seconds")
    val tsEpochSeconds: Long
)

