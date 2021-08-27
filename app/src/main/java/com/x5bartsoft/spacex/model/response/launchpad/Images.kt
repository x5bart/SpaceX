package com.x5bartsoft.spacex.model.response.launchpad


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("large")
    val large: List<String>
)