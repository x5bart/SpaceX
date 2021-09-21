package com.x5bartsoft.spacex.model.response.rockets


import com.google.gson.annotations.SerializedName

data class Isp(
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("vacuum")
    val vacuum: Int
)