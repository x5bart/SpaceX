package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName

data class SelectRockets(
    @SerializedName("name")
    val name: Int = 1
)