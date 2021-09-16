package com.x5bartsoft.spacex.model.request.querylaunches


import com.google.gson.annotations.SerializedName

data class LaunchesRequest(
    @SerializedName("options")
    val options: Options,
    @SerializedName("query")
    val query: Query
)