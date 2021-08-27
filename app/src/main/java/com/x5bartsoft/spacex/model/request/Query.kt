package com.x5bartsoft.spacex.model.request


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("launchpad")
    val launchpad: List<String>,
    @SerializedName("rocket")
    val rocket: List<String>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("upcoming")
    val upcoming: Boolean
)