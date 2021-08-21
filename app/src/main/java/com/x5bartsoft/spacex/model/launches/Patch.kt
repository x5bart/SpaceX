package com.x5bartsoft.spacex.model.launches


import com.google.gson.annotations.SerializedName

data class Patch(
    @SerializedName("large")
    val large: String,
    @SerializedName("small")
    val small: String
)