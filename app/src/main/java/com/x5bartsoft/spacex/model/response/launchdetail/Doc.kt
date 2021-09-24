package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Doc(
    @SerializedName("capsules")
    val capsules:  List<Capsule>,
    @SerializedName("cores")
    val cores:  List<Core>,
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("details")
    val details: String,
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("launchpad")
    val launchpad:  Launchpad,
    @SerializedName("links")
    val links:  Links,
    @SerializedName("name")
    val name: String,
    @SerializedName("payloads")
    val payloads:  List<Payload>,
    @SerializedName("rocket")
    val rocket:  RocketX,
    @SerializedName("ships")
    val ships:  List<Ship>,
    @SerializedName("success")
    val success: Boolean
) : Parcelable