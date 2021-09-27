package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_LAUNCHPAD

data class PopulateLauchpad(
    @SerializedName("path")
    val path: String = QUERY_LAUNCHPAD,
    @SerializedName("populate")
    val populate: List<Any> = listOf(PopulateRockets(), PopulateLaunches()),
    @SerializedName("select")
    val select: SelectLaunchpad = SelectLaunchpad(),
)