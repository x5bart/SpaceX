package com.x5bartsoft.spacex.data.network

import com.x5bartsoft.spacex.model.request.Query
import com.x5bartsoft.spacex.model.request.QueryLaunches
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.model.response.launchpad.Launchpads
import com.x5bartsoft.spacex.model.rockets.Rockets
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SpaceXApi {

    @POST("/v4/launches/query")
    suspend fun getAllLaunches(@Body query: QueryLaunches?): Response<Launches>

    @GET("/v4/rockets")
    suspend fun getAllRockets(): Response<Rockets>

    @GET("v4/launchpads")
    suspend fun getAllLaunchpads(): Response<Launchpads>
}