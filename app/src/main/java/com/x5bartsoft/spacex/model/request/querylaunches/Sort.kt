package com.x5bartsoft.spacex.model.request.querylaunches


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.DESC

data class Sort(
    @SerializedName("flight_number")
    val flightNumber: String = DESC
)