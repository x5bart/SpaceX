package com.x5bartsoft.spacex.model.request


import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("flight_number")
    val flightNumber: String
)