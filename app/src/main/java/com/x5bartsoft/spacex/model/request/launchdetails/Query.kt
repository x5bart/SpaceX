package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("name")
    val name: String
)