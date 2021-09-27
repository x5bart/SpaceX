package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class SelectRocket(
    @SerializedName("active")
    val active: Int = 1,

    @SerializedName("boosters")
    val boosters: Int = 1,

    @SerializedName("cost_per_launch")
    val costPerLaunch: Int = 1,

    @SerializedName("company")
    val company: Int = 1,

    @SerializedName("country")
    val country: Int = 1,

    @SerializedName("description")
    val description: Int = 1,

    @SerializedName("diameter")
    val diameter: Int = 1,

    @SerializedName("engines")
    val engines: Int = 1,

    @SerializedName("first_flight")
    val firstFlight: Int = 1,

    @SerializedName("first_stage")
    val firstStage: Int = 1,

    @SerializedName("flickr_images")
    val flickrImages: Int = 1,

    @SerializedName("height")
    val height: Int = 1,

    @SerializedName("landing_legs")
    val landingLegs: Int = 1,

    @SerializedName("mass")
    val mass: Int = 1,

    @SerializedName("name")
    val name: Int = 1,

    @SerializedName("payload_weights")
    val payloadWeights: Int = 1,

    @SerializedName("second_stage")
    val secondStage: Int = 1,

    @SerializedName("stages")
    val stages: Int = 1,

    @SerializedName("success_rate_pct")
    val successRatePct: Int = 1,

    @SerializedName("wikipedia")
    val wikipedia: Int = 1,
)