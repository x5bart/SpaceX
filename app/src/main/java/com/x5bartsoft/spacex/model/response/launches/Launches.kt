package com.x5bartsoft.spacex.model.response.launches


import com.google.gson.annotations.SerializedName

data class Launches(
    @SerializedName("docs")
    val docs: List<Doc>,
)