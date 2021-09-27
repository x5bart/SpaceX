package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payload(
    @SerializedName("apoapsis_km")
    val apoapsisKm: Double,
    @SerializedName("customers")
    val customers: List<String>,
    @SerializedName("eccentricity")
    val eccentricity: Double,
    @SerializedName("id")
    val id: String,
    @SerializedName("lifespan_years")
    val lifespanYears: Int,
    @SerializedName("manufacturers")
    val manufacturers: List<String>,
    @SerializedName("mass_kg")
    val massKg: Double,
    @SerializedName("mass_lbs")
    val massLbs: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationalities")
    val nationalities: List<String>,
    @SerializedName("orbit")
    val orbit: String,
    @SerializedName("periapsis_km")
    val periapsisKm: Double,
    @SerializedName("period_min")
    val periodMin: Double,
    @SerializedName("reference_system")
    val referenceSystem: String,
    @SerializedName("regime")
    val regime: String,
    @SerializedName("semi_major_axis_km")
    val semiMajorAxisKm: Double
) : Parcelable