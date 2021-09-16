package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Height(
    @SerializedName("feet")
    val feet: Double,
    @SerializedName("meters")
    val meters: Int
) : Parcelable