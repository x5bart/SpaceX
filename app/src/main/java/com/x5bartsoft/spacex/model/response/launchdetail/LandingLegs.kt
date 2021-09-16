package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LandingLegs(
    @SerializedName("material")
    val material: String,
    @SerializedName("number")
    val number: Int
) : Parcelable