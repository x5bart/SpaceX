package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName

data class SelectRocket(
    @SerializedName("company")
    val company: Int = 1,
    @SerializedName("country")
    val country: Int = 1,
    @SerializedName("description")
    val description: Int = 1,
    @SerializedName("name")
    val name: Int = 1,
    @SerializedName("wikipedia")
    val wikipedia: Int = 1,
)