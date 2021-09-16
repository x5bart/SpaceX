package com.x5bartsoft.spacex.model.request.querylaunches


import com.google.gson.annotations.SerializedName

data class Flickr(
    @SerializedName("original")
    val original: Int = 1
)