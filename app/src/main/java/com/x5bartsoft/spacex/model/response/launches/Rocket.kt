package com.x5bartsoft.spacex.model.response.launches


import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)