package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class Options(


    @SerializedName("populate")
    val populate: List<Any> = listOf(
        PopulateRocket(),
        PopulateShips(),
        PopulateCapsules(),
        PopulatePayloads(),
        PopulateLaunchpad(),
        PopulateCores(),
        PopulateCrew()
        ),
    @SerializedName("select")
    val select: SelectLaunch = SelectLaunch(),
)