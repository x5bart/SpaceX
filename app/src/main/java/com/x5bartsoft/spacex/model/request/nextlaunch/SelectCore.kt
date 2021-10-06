package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName

data class SelectCore(
    @SerializedName("asds_attempts")
    val asdsAttempts: Int = 1,
    @SerializedName("asds_landings")
    val asdsLandings: Int = 1,
    @SerializedName("block")
    val block: Int = 1,
    @SerializedName("last_update")
    val lastUpdate: Int = 1,
    @SerializedName("reuse_count")
    val reuseCount: Int = 1,
    @SerializedName("rtls_attempts")
    val rtlsAttempts: Int = 1,
    @SerializedName("rtls_landings")
    val rtlsLandings: Int = 1,
    @SerializedName("serial")
    val serial: Int = 1,
    @SerializedName("status")
    val status: Int = 1
)