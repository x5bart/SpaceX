package com.x5bartsoft.spacex.model.request


import com.google.gson.annotations.SerializedName

data class QueryLaunches(
    @SerializedName("options")
    val options: Options,
    @SerializedName("query")
    val query: Query
)