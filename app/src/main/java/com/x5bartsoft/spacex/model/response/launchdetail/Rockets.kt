package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rockets(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
) : Parcelable