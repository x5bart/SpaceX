package com.x5bartsoft.spacex.model.response.rockets


import com.google.gson.annotations.SerializedName

data class Height(
    @SerializedName("feet")
    val feet: Double,
    @SerializedName("meters")
    val meters: Double
)