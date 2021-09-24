package com.x5bartsoft.spacex.model.response.upcomminglaunch


import com.google.gson.annotations.SerializedName

data class Patch(
    @SerializedName("large")
    val large: Any,
    @SerializedName("small")
    val small: Any
)