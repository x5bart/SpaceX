package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Capsule(
    @SerializedName("id")
    val id: String,
    @SerializedName("last_update")
    val lastUpdate: String,
    @SerializedName("launches")
    val launches: List<Launches>,
    @SerializedName("reuse_count")
    val reuseCount: Int,
    @SerializedName("serial")
    val serial: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("water_landings")
    val waterLandings: Int
) : Parcelable