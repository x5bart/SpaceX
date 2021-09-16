package com.x5bartsoft.spacex.model.response.launches


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Doc(
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("details")
    val details: String?,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("launchpad")
    val launchpad: @RawValue Launchpad,
    @SerializedName("links")
    val links: @RawValue Links,
    @SerializedName("name")
    val name: String,
    @SerializedName("rocket")
    val rocket: @RawValue Rocket
): Parcelable