package com.x5bartsoft.spacex.model


import com.google.gson.annotations.SerializedName

data class Reddit(
    @SerializedName("campaign")
    val campaign: String,
    @SerializedName("launch")
    val launch: String,
    @SerializedName("media")
    val media: Any,
    @SerializedName("recovery")
    val recovery: String
)