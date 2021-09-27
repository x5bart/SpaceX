package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reddit(
    @SerializedName("campaign")
    val campaign: String?,
    @SerializedName("launch")
    val launch: String?,
    @SerializedName("media")
    val media: String?,
    @SerializedName("recovery")
    val recovery: String?
) : Parcelable