package com.x5bartsoft.spacex.model.request


import com.google.gson.annotations.SerializedName

data class Select(
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("name")
    val name: Int = 1
)