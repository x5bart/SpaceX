package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Engines(
    @SerializedName("engine_loss_max")
    val engineLossMax: Int,
    @SerializedName("isp")
    val isp:@RawValue Isp,
    @SerializedName("layout")
    val layout: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("propellant_1")
    val propellant1: String,
    @SerializedName("propellant_2")
    val propellant2: String,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel:@RawValue ThrustSeaLevel,
    @SerializedName("thrust_to_weight")
    val thrustToWeight: Double,
    @SerializedName("thrust_vacuum")
    val thrustVacuum:@RawValue ThrustVacuum,
    @SerializedName("type")
    val type: String,
    @SerializedName("version")
    val version: String
) : Parcelable