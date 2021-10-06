package com.x5bartsoft.spacex.model.request.nextlaunch

import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("flight_number")
    val flightNumber:String = "asc"
)
