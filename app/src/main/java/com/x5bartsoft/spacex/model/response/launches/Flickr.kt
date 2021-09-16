package com.x5bartsoft.spacex.model.response.launches


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Flickr(
    @SerializedName("original")
    val original: @RawValue List<String>?
):Parcelable