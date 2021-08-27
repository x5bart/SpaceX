package com.x5bartsoft.spacex.model.response.launches


import com.google.gson.annotations.SerializedName

data class Doc(
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("details")
    val details: String,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("launchpad")
    val launchpad: Launchpad,
    @SerializedName("links")
    val links: Links,
    @SerializedName("name")
    val name: String,
    @SerializedName("rocket")
    val rocket: Rocket
)