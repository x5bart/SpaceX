package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Landpad(
    @SerializedName("details")
    val details: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("landing_attempts")
    val landingAttempts: Int,
    @SerializedName("landing_successes")
    val landingSuccesses: Int,
    @SerializedName("launches")
    val launches: List<Launches>,
    @SerializedName("locality")
    val locality: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("wikipedia")
    val wikipedia: String
) : Parcelable