package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName

data class NextLaunchRequest(
    @SerializedName("options")
    val options: Options = Options(),
    @SerializedName("query")
    val query: Query = Query()
)