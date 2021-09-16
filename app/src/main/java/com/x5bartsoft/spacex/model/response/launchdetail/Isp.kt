package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Isp(
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("vacuum")
    val vacuum: Int
) : Parcelable