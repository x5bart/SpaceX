package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_ROCKET

data class PopulateRocket(
    @SerializedName("path")
    val path: String = QUERY_ROCKET,
    @SerializedName("select")
    val select: SelectRocket = SelectRocket()
)