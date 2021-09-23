package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class Select(
    @SerializedName("date_local")
    val dateLocal: Int = 1,
    @SerializedName("details")
    val details: Int= 1,
    @SerializedName("flight_number")
    val flightNumber: Int= 1,
    @SerializedName("full_name")
    val fullName: Int= 1,
    @SerializedName("id")
    val id: Int= 1,
    @SerializedName("images")
    val images: Int= 1,
    @SerializedName("locality")
    val locality: Int= 1,
    @SerializedName("name")
    val name: Int= 1,
    @SerializedName("region")
    val region: Int= 1,
    @SerializedName("serial")
    val serial: Int= 1
)