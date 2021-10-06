package com.x5bartsoft.spacex.model.request.nextlaunch


import com.google.gson.annotations.SerializedName

data class SelectShips(
    @SerializedName("abs")
    val abs: Int = 1,
    @SerializedName("active")
    val active: Int = 1,
    @SerializedName("image")
    val image: Int = 1,
    @SerializedName("imo")
    val imo: Int = 1,
    @SerializedName("link")
    val link: Int = 1,
    @SerializedName("mass")
    val mass: Int = 1,
    @SerializedName("mass_kg")
    val massKg: Int = 1,
    @SerializedName("mass_lbs")
    val massLbs: Int = 1,
    @SerializedName("mmsi")
    val mmsi: Int = 1,
    @SerializedName("name")
    val name: Int = 1,
    @SerializedName("roles")
    val roles: Int = 1,
    @SerializedName("type")
    val type: Int = 1,
    @SerializedName("year_built")
    val yearBuilt: Int = 1,
    @SerializedName("home_port")
    val homePort: Int = 1
)