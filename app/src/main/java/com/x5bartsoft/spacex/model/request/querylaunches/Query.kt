package com.x5bartsoft.spacex.model.request.querylaunches


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("launchpad")
    var launchpad: Set<String>?,
    @SerializedName("rocket")
    var rocket: Set<String>?,
    @SerializedName("success")
    val success: String?,
    @SerializedName("upcoming")
    var upcoming: Boolean = false
)