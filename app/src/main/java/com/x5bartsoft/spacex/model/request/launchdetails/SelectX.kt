package com.x5bartsoft.spacex.model.request.launchdetails


import com.google.gson.annotations.SerializedName

data class SelectX(
    @SerializedName("active")
    val active: Int = 1,
    @SerializedName("boosters")
    val boosters: Int = 1,
    @SerializedName("company")
    val company: Int = 1,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int = 1,
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
    @SerializedName("flight")
    val flight: Int = 1,
    @SerializedName("height")
    val height: Int = 1,
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("image")
    val image: Int = 1,
    @SerializedName("landing_legs")
    val landingLegs: Int = 1,
    @SerializedName("link")
    val link: Int = 1,
    @SerializedName("mass")
    val mass: Int = 1,
    @SerializedName("name")
    val name: Int = 1,
    @SerializedName("payload_weights")
    val payloadWeights: Int = 1,
    @SerializedName("roles")
    val roles: Int = 1,
    @SerializedName("second_stage")
    val secondStage: Int = 1,
    @SerializedName("serial")
    val serial: Int = 1,
    @SerializedName("stages")
    val stages: Int = 1,
    @SerializedName("success_rate_pct")
    val successRatePct: Int = 1,
    @SerializedName("type")
    val type: Int = 1,
    @SerializedName("wikipedia")
    val wikipedia: Int = 1,
    @SerializedName("year_built")
    val yearBuilt: Int = 1,
    @SerializedName("details")
    val details: Int = 1,
    @SerializedName("full_name")
    val fullName: Int = 1,
    @SerializedName("images")
    val images: Int = 1,
    @SerializedName("launch_attempts")
    val launchAttempts: Int = 1,
    @SerializedName("launch_successes")
    val launchSuccesses: Int = 1,
    @SerializedName("locality")
    val locality: Int = 1,
    @SerializedName("region")
    val region: Int = 1,
    @SerializedName("status")
    val status: Int = 1,
)