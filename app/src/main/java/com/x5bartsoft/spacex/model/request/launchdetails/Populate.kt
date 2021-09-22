package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class Populate(
    @SerializedName("path")
    val path: String,
    @SerializedName("populate")
    val populate: List<Populate>?,
    @SerializedName("select")
    val select: Select
)