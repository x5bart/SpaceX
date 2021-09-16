package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mass(
    @SerializedName("kg")
    val kg: Int,
    @SerializedName("lb")
    val lb: Int
) : Parcelable