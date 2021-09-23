package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class PopulateX(
    @SerializedName("path")
    val path: String,
    @SerializedName("select")
    val select: Select = Select()
)