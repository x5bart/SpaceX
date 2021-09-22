package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class SelectLaunch(
    @SerializedName("date_local")
    val dateLocal: Int = 1,
    @SerializedName("details")
    val details: Int = 1,
    @SerializedName("flight_number")
    val flightNumber: Int = 1,
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("links")
    val links: Int = 1,
    @SerializedName("name")
    val name: Int = 1,
    @SerializedName("success")
    val success: Int = 1,
)