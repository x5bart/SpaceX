package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class SecondStage(
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int,
    @SerializedName("engines")
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Int,
    @SerializedName("payloads")
    val payloads: Payloads,
    @SerializedName("reusable")
    val reusable: Boolean,
    @SerializedName("thrust")
    val thrust:@RawValue Thrust
) : Parcelable