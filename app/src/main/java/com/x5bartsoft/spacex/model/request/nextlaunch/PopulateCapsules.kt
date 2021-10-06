package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_CAPSULES

data class PopulateCapsules(
    @SerializedName("path")
    val path: String = QUERY_CAPSULES,
    @SerializedName("select")
    val select: SelectCapsules = SelectCapsules()
)