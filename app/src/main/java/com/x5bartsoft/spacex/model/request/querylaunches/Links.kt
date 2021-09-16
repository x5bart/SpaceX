package com.x5bartsoft.spacex.model.request.querylaunches


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("flickr")
    val flickr: Flickr = Flickr(),
    @SerializedName("patch")
    val patch: Patch = Patch()
)