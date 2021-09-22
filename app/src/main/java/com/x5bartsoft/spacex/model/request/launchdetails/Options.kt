package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class Options(
    @SerializedName("populate")
    val populate: List<Populate>,
    @SerializedName("select")
    val select: SelectLaunch = SelectLaunch()
)