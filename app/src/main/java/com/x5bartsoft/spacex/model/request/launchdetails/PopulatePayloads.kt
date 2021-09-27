package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_PAYLOADS

data class PopulatePayloads(
    @SerializedName("path")
    val path: String = QUERY_PAYLOADS,
    @SerializedName("select")
    val select: SelectPayloads = SelectPayloads()
)