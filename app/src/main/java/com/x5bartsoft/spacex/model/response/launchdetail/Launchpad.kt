package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Launchpad(
    @SerializedName("details")
    val details: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: ImagesLaunchpad,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("launch_attempts")
    val launchAttempts: Int,
    @SerializedName("launch_successes")
    val launchSuccesses: Int,
    @SerializedName("launches")
    val launches: List<Launche>,
    @SerializedName("locality")
    val locality: String,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("rockets")
    val rockets:@RawValue List<Rocket>,
    @SerializedName("status")
    val status: String,
    @SerializedName("timezone")
    val timezone: String
) : Parcelable