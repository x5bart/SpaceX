package com.x5bartsoft.spacex.model.response.rockets


import com.google.gson.annotations.SerializedName

data class CompositeFairing(
    @SerializedName("diameter")
    val diameter: Diameter,
    @SerializedName("height")
    val height: Height
)