package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_CREW

data class PopulateCrew(
    @SerializedName("path")
    val path: String = QUERY_CREW,
    @SerializedName("populate")
    val populate: List<Any> = listOf(PopulateLaunches()),
    @SerializedName("select")
    val select: SelectCrew = SelectCrew()
)