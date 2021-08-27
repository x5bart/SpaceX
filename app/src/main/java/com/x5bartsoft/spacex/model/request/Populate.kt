package com.x5bartsoft.spacex.model.request


import com.google.gson.annotations.SerializedName

data class Populate(
    @SerializedName("path")
    val path: String,
    @SerializedName("select")
    val select: Select
)