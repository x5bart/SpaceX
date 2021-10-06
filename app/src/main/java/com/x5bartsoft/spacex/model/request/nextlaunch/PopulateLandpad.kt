package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_LANDPAD

data class PopulateLandpad(
    @SerializedName("path")
    val path: String = QUERY_LANDPAD,
    @SerializedName("populate")
    val populate: List<Any> = listOf(PopulateLaunches()),
    @SerializedName("select")
    val select: SelectLandpad = SelectLandpad()
)