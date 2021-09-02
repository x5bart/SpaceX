package com.x5bartsoft.spacex.model.request


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("launchpad")
    var launchpad: List<String>?,
    @SerializedName("rocket")
    var rocket: List<String>?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("upcoming")
    var upcoming: Boolean
)