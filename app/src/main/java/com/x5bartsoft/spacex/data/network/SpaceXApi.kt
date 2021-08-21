package com.x5bartsoft.spacex.data.network

import com.x5bartsoft.spacex.model.launches.Launch
import com.x5bartsoft.spacex.model.launchpad.Launchpads
import com.x5bartsoft.spacex.model.rockets.Rockets
import retrofit2.Response
import retrofit2.http.GET

interface SpaceXApi {

    @GET("/v4/launches")
    suspend fun getAllLaunches(): Response<List<Launch>>

    @GET("/v4/rockets")
    suspend fun getAllRockets(): Response<Rockets>

    @GET("v4/launchpads")
    suspend fun getAllLaunchpads():Response<Launchpads>
}