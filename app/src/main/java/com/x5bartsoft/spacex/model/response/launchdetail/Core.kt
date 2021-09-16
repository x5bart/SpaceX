package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Core(
    @SerializedName("core")
    val core:@RawValue CoreX,
    @SerializedName("flight")
    val flight: Int,
    @SerializedName("gridfins")
    val gridfins: Boolean,
    @SerializedName("landing_attempt")
    val landingAttempt: Boolean,
    @SerializedName("landing_success")
    val landingSuccess: Boolean,
    @SerializedName("landing_type")
    val landingType: String,
    @SerializedName("landpad")
    val landpad:@RawValue Landpad,
    @SerializedName("legs")
    val legs: Boolean,
    @SerializedName("reused")
    val reused: Boolean
) : Parcelable