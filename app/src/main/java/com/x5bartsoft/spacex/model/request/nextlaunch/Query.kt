package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("upcoming")
    val upcoming: Boolean = true
)