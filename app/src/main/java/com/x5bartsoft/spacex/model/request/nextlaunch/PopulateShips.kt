package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_SHIPS

data class PopulateShips(
    @SerializedName("path")
    val path: String = QUERY_SHIPS,
    @SerializedName("select")
    val select: SelectShips = SelectShips()
)