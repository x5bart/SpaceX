package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class FirstStage(
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int,
    @SerializedName("engines")
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Int,
    @SerializedName("reusable")
    val reusable: Boolean,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel:@RawValue ThrustSeaLevel,
    @SerializedName("thrust_vacuum")
    val thrustVacuum:@RawValue ThrustVacuum
) : Parcelable