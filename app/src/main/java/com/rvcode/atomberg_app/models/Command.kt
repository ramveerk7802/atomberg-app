package com.rvcode.atomberg_app.models

import com.google.gson.annotations.SerializedName

data class Command(
    val power: Boolean? = null,
    val speed: Int? = null,
    val speedDelta: Int? = null,
    val sleep: Boolean? = null,
    val timer: Int? = null,
    val led: Boolean? = null,
    val brightness: Int? = null,
    val brightnessDelta: Int? = null,
    @SerializedName("light_mode")
    val lightMode: String? = null
)

