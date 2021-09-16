package com.x5bartsoft.spacex.model.request.querylaunches


import com.google.gson.annotations.SerializedName
import com.x5bartsoft.spacex.util.Constants.Companion.DEFAULT_LIMIT

data class Options(
    @SerializedName("populate")
    val populate: List<Populate>,
    @SerializedName("limit")
    val limit: Int = DEFAULT_LIMIT,
    @SerializedName("select")
    val select: SelectX = SelectX(),
    @SerializedName("sort")
    val sort: Sort = Sort(),
)