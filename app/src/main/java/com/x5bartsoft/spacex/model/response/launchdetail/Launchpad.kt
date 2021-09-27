package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Launchpad(
    @SerializedName("details")
    val details: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("launch_attempts")
    val launchAttempts: Int,
    @SerializedName("launch_successes")
    val launchSuccesses: Int,
    @SerializedName("launches")
    val launches: List<Launches>,
    @SerializedName("locality")
    val locality: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("rockets")
    val rockets: List<Rockets>,
    @SerializedName("status")
    val status: String
) : Parcelable