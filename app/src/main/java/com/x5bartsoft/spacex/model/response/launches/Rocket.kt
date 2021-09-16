package com.x5bartsoft.spacex.model.response.launches


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rocket(
    @SerializedName("name")
    val name: String
):Parcelable