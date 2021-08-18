package com.x5bartsoft.spacex.model


import com.google.gson.annotations.SerializedName

data class Core(
    @SerializedName("block")
    val block: Any,
    @SerializedName("core_serial")
    val coreSerial: String,
    @SerializedName("flight")
    val flight: Int,
    @SerializedName("gridfins")
    val gridfins: Boolean,
    @SerializedName("land_success")
    val landSuccess: Any,
    @SerializedName("landing_intent")
    val landingIntent: Boolean,
    @SerializedName("landing_type")
    val landingType: Any,
    @SerializedName("landing_vehicle")
    val landingVehicle: Any,
    @SerializedName("legs")
    val legs: Boolean,
    @SerializedName("reused")
    val reused: Boolean
)