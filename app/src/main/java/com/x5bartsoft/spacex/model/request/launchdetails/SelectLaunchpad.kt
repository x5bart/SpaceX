package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class SelectLaunchpad(
    @SerializedName("details")
    val details: Int = 1,
    @SerializedName("full_name")
    val fullName: Int = 1,
    @SerializedName("images")
    val images:Int = 1,
    @SerializedName("launch_attempts")
    val launchAttempts: Int = 1,
    @SerializedName("launch_successes")
    val launchSuccesses: Int = 1,
    @SerializedName("locality")
    val locality: Int = 1,
    @SerializedName("name")
    val name: Int = 1,
    @SerializedName("region")
    val region: Int = 1,
    @SerializedName("status")
    val status: Int = 1,
)