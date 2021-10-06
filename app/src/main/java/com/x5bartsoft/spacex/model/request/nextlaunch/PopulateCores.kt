package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_CORES

data class PopulateCores(
    @SerializedName("path")
    val path: String = QUERY_CORES,
    @SerializedName("select")
    val select: SelectCores = SelectCores(),
)