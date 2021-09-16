package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Dragon(
    @SerializedName("capsule")
    val capsule:@RawValue Any,
    @SerializedName("flight_time_sec")
    val flightTimeSec:@RawValue Any,
    @SerializedName("land_landing")
    val landLanding:@RawValue Any,
    @SerializedName("manifest")
    val manifest:@RawValue Any,
    @SerializedName("mass_returned_kg")
    val massReturnedKg:@RawValue Any,
    @SerializedName("mass_returned_lbs")
    val massReturnedLbs:@RawValue Any,
    @SerializedName("water_landing")
    val waterLanding:@RawValue Any
) : Parcelable