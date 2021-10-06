package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName

data class SelectPayloads(
    @SerializedName("apoapsis_km")
    val apoapsisKm: Int = 1,
    @SerializedName("customers")
    val customers: Int = 1,
    @SerializedName("lifespan_years")
    val lifespanYears: Int = 1,
    @SerializedName("manufacturers")
    val manufacturers: Int = 1,
    @SerializedName("mass_kg")
    val massKg: Int = 1,
    @SerializedName("mass_lbs")
    val massLbs: Int = 1,
    @SerializedName("mmsi")
    val mmsi: Int = 1,
    @SerializedName("name")
    val name: Int = 1,
    @SerializedName("nationalities")
    val nationalities: Int = 1,
    @SerializedName("orbit")
    val orbit: Int = 1,
    @SerializedName("periapsis_km")
    val periapsisKm: Int = 1,
    @SerializedName("period_min")
    val periodMin: Int = 1,
    @SerializedName("reference_system")
    val referenceSystem: Int = 1,
    @SerializedName("regime")
    val regime: Int = 1,
    @SerializedName("semi_major_axis_km")
    val semiMajorAxisKm: Int = 1,

)