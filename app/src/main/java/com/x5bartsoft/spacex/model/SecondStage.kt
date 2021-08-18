package com.x5bartsoft.spacex.model


import com.google.gson.annotations.SerializedName

data class SecondStage(
    @SerializedName("block")
    val block: Int,
    @SerializedName("payloads")
    val payloads: List<Payload>
)