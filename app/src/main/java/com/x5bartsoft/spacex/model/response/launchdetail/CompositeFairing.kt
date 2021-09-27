package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompositeFairing(
    @SerializedName("diameter")
    val diameter: Diameter,
    @SerializedName("height")
    val height: Height
) : Parcelable