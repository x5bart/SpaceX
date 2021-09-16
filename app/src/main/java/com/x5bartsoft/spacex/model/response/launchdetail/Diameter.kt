package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Diameter(
    @SerializedName("feet")
    val feet: Int,
    @SerializedName("meters")
    val meters: Double
) : Parcelable