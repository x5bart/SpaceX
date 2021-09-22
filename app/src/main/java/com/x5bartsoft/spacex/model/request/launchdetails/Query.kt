package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("flight_number")
    val flightNumber: Int
)