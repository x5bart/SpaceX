package com.x5bartsoft.spacex.model


import com.google.gson.annotations.SerializedName

data class Telemetry(
    @SerializedName("flight_club")
    val flightClub: Any
)