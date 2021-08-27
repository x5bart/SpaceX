package com.x5bartsoft.spacex.model.request


import com.google.gson.annotations.SerializedName

data class Options(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("populate")
    val populate: List<Populate>,
    @SerializedName("select")
    val select: SelectX,
    @SerializedName("sort")
    val sort: Sort
)