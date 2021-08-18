package com.x5bartsoft.spacex.model


import com.google.gson.annotations.SerializedName

data class LaunchFailureDetails(
    @SerializedName("altitude")
    val altitude: Any,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("time")
    val time: Int
)