package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class CompositeFairing(
    @SerializedName("diameter")
    val diameter:@RawValue Diameter,
    @SerializedName("height")
    val height:@RawValue Height
) : Parcelable