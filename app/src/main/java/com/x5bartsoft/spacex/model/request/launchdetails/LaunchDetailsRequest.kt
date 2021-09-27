package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class LaunchDetailsRequest(
    @SerializedName("options")
    val options: Options = Options(),
    @SerializedName("query")
    val query: Query
)