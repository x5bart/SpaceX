package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Doc(
    @SerializedName("capsules")
    val capsules: @RawValue List<Capsule>,
    @SerializedName("cores")
    val cores: @RawValue List<Core>,
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("details")
    val details: String,
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
    @SerializedName("payloads")
    val payloads: @RawValue List<Payload>,
    @SerializedName("rocket")
    val rocket: @RawValue RocketX,
    @SerializedName("ships")
    val ships: @RawValue List<Ship>,
    @SerializedName("success")
    val success: Boolean
) : Parcelable