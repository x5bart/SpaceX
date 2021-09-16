package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Patch(
    @SerializedName("large")
    val large: String,
    @SerializedName("small")
    val small: String
) : Parcelable