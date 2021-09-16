package com.x5bartsoft.spacex.model.response.launchdetail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Payload(
    @SerializedName("apoapsis_km")
    val apoapsisKm:@RawValue Any,
    @SerializedName("arg_of_pericenter")
    val argOfPericenter:@RawValue Any,
    @SerializedName("customers")
    val customers:@RawValue List<Any>,
    @SerializedName("dragon")
    val dragon: Dragon,
    @SerializedName("eccentricity")
    val eccentricity:@RawValue Any,
    @SerializedName("epoch")
    val epoch:@RawValue Any,
    @SerializedName("id")
    val id: String,
    @SerializedName("inclination_deg")
    val inclinationDeg:@RawValue Any,
    @SerializedName("launch")
    val launch: String,
    @SerializedName("lifespan_years")
    val lifespanYears:@RawValue Any,
    @SerializedName("longitude")
    val longitude:@RawValue Any,
    @SerializedName("manufacturers")
    val manufacturers:@RawValue List<Any>,
    @SerializedName("mass_kg")
    val massKg:@RawValue Any,
    @SerializedName("mass_lbs")
    val massLbs:@RawValue Any,
    @SerializedName("mean_anomaly")
    val meanAnomaly:@RawValue Any,
    @SerializedName("mean_motion")
    val meanMotion:@RawValue Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationalities")
    val nationalities: List<String>,
    @SerializedName("norad_ids")
    val noradIds:@RawValue List<Any>,
    @SerializedName("orbit")
    val orbit: String,
    @SerializedName("periapsis_km")
    val periapsisKm:@RawValue Any,
    @SerializedName("period_min")
    val periodMin:@RawValue Any,
    @SerializedName("raan")
    val raan:@RawValue Any,
    @SerializedName("reference_system")
    val referenceSystem: String,
    @SerializedName("regime")
    val regime: String,
    @SerializedName("reused")
    val reused: Boolean,
    @SerializedName("semi_major_axis_km")
    val semiMajorAxisKm:@RawValue Any,
    @SerializedName("type")
    val type: String
) : Parcelable