package com.x5bartsoft.spacex.model.request.querylaunches


import com.google.gson.annotations.SerializedName

data class SelectX(
    @SerializedName("date_local")
    val dateLocal: Int = 1,
    @SerializedName("details")
    val details: Int = 1,
    @SerializedName("flight_number")
    val flightNumber: Int = 1,
    @SerializedName("links")
    val links: Links = Links(Flickr(1), Patch()),
    @SerializedName("name")
    val name: Int = 1,
)