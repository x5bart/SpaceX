package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Core(
    @SerializedName("asds_attempts")
    val asdsAttempts: Int,
    @SerializedName("asds_landings")
    val asdsLandings: Int,
    @SerializedName("block")
    val block: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_update")
    val lastUpdate: String,
    @SerializedName("launches")
    val launches: List<Launches>,
    @SerializedName("reuse_count")
    val reuseCount: Int,
    @SerializedName("reused")
    val reused: Boolean,
    @SerializedName("rtls_attempts")
    val rtlsAttempts: Int,
    @SerializedName("rtls_landings")
    val rtlsLandings: Int,
    @SerializedName("serial")
    val serial: String,
    @SerializedName("status")
    val status: String,

) : Parcelable