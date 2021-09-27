package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_ROCKETS

data class PopulateRockets(
    @SerializedName("path")
    val path: String = QUERY_ROCKETS,
    @SerializedName("select")
    val select: SelectRockets = SelectRockets()
)