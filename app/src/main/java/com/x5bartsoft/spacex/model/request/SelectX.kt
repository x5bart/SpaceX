package com.x5bartsoft.spacex.model.request


import com.google.gson.annotations.SerializedName

data class SelectX(
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
)