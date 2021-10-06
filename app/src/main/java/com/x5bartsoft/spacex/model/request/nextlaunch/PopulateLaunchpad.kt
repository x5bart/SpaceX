package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_LAUNCHPAD

data class PopulateLaunchpad(
    @SerializedName("path")
    val path: String = QUERY_LAUNCHPAD,
    @SerializedName("select")
    val select: SelectLaunchpad = SelectLaunchpad(),
)