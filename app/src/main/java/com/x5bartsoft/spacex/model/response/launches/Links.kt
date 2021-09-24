package com.x5bartsoft.spacex.model.response.launches


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Links(
    @SerializedName("flickr")
    val flickr:Flickr?,
    @SerializedName("patch")
    val patch:  Patch?,
):Parcelable