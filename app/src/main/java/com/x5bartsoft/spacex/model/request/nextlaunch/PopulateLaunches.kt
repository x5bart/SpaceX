package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_LAUNCHES

data class PopulateLaunches(
    @SerializedName("path")
    val path: String = QUERY_LAUNCHES,
    @SerializedName("select")
    val select: SelectLaunches = SelectLaunches()
)