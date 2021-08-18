package com.x5bartsoft.spacex.model


import com.google.gson.annotations.SerializedName

data class Timeline(
    @SerializedName("webcast_liftoff")
    val webcastLiftoff: Int
)