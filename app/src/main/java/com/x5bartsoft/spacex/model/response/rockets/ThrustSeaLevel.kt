package com.x5bartsoft.spacex.model.response.rockets


import com.google.gson.annotations.SerializedName

data class ThrustSeaLevel(
    @SerializedName("kN")
    val kN: Int,
    @SerializedName("lbf")
    val lbf: Int
)