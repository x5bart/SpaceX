package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flickr(
    @SerializedName("original")
    val original: List<String>,
    @SerializedName("small")
    val small: List<String>
) : Parcelable