package com.x5bartsoft.spacex.model.response.launchdetail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Capsule(
    @SerializedName("id")
    val id: String,
    @SerializedName("serial")
    val serial: String,
    @SerializedName("type")
    val type: String
) : Parcelable