package com.x5bartsoft.spacex.model.response.launches


import com.google.gson.annotations.SerializedName

data class Flickr(
    @SerializedName("original")
    val original: List<String>,
    @SerializedName("small")
    val small: List<Any>
)