package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Reddit(
    @SerializedName("campaign")
    val campaign: String?,
    @SerializedName("launch")
    val launch: String?,
    @SerializedName("media")
    val media:@RawValue String?,
    @SerializedName("recovery")
    val recovery: String?
) : Parcelable