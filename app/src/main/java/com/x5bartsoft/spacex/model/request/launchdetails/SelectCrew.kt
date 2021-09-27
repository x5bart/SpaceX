package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class SelectCrew(
    @SerializedName("agency")
    val agency: Int = 1,
    @SerializedName("image")
    val image: Int = 1,
    @SerializedName("name")
    val name: Int = 1,
    @SerializedName("status")
    val status: Int = 1,
    @SerializedName("wikipedia")
    val wikipedia: Int = 1,
)