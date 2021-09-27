package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class SelectCapsules(
    @SerializedName("last_update")
    val lastUpdate: Int = 1,
    @SerializedName("reuse_count")
    val reuseCount: Int = 1,
    @SerializedName("serial")
    val serial: Int = 1,
    @SerializedName("status")
    val status: Int = 1,
    @SerializedName("type")
    val type: Int = 1,
    @SerializedName("water_landings")
    val waterLandings: Int = 1
)