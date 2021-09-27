package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ship(
    @SerializedName("abs")
    val abs: Int,
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("home_port")
    val homePort: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("imo")
    val imo: Int,
    @SerializedName("launches")
    val launches: List<Launches>,
    @SerializedName("link")
    val link: String,
    @SerializedName("mass_kg")
    val massKg: Int,
    @SerializedName("mass_lbs")
    val massLbs: Int,
    @SerializedName("mmsi")
    val mmsi: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("type")
    val type: String,
    @SerializedName("year_built")
    val yearBuilt: Int
) : Parcelable