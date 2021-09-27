package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class SelectLaunches(
    @SerializedName("date_local")
    val dateLocal: Int = 1,
    @SerializedName("flight_number")
    val flightNumber: Int = 1,
    @SerializedName("  name")
    val name: Int = 1
)