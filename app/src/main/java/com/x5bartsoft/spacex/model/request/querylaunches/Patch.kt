package com.x5bartsoft.spacex.model.request.querylaunches


import com.google.gson.annotations.SerializedName

data class Patch(
    @SerializedName("small")
    val small: Int = 1
)