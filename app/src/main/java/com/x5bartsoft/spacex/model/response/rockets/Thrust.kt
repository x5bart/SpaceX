package com.x5bartsoft.spacex.model.response.rockets


import com.google.gson.annotations.SerializedName

data class Thrust(
    @SerializedName("kN")
    val kN: Int,
    @SerializedName("lbf")
    val lbf: Int
)