package com.x5bartsoft.spacex.model.launchpad


import com.google.gson.annotations.SerializedName

data class LaunchpadsItem(
    @SerializedName("details")
    val details: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("launch_attempts")
    val launchAttempts: Int,
    @SerializedName("launch_successes")
    val launchSuccesses: Int,
    @SerializedName("launches")
    val launches: List<String>,
    @SerializedName("locality")
    val locality: String,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("rockets")
    val rockets: List<String>,
    @SerializedName("status")
    val status: String,
    @SerializedName("timezone")
    val timezone: String
)