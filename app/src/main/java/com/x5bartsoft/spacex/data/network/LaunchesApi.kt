package com.x5bartsoft.spacex.data.network

import com.x5bartsoft.spacex.model.Launch
import com.x5bartsoft.spacex.model.Launches
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface LaunchesApi {

    @GET("/launches")
    suspend fun getAllLaunches(): Response<Launches>
}