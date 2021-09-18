package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Links(
    @SerializedName("article")
    val article:@RawValue String,
    @SerializedName("flickr")
    val flickr: Flickr,
    @SerializedName("patch")
    val patch:@RawValue Patch,
    @SerializedName("presskit")
    val presskit:@RawValue String,
    @SerializedName("reddit")
    val reddit:@RawValue Reddit,
    @SerializedName("webcast")
    val webcast: String,
    @SerializedName("wikipedia")
    val wikipedia:@RawValue String,
    @SerializedName("youtube_id")
    val youtubeId: String
) : Parcelable