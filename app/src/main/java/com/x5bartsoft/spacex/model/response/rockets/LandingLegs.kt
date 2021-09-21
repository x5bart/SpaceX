package com.x5bartsoft.spacex.model.response.rockets


import com.google.gson.annotations.SerializedName

data class LandingLegs(
    @SerializedName("material")
    val material: Any,
    @SerializedName("number")
    val number: Int
)