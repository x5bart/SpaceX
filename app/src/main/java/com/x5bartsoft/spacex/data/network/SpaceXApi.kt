package com.x5bartsoft.spacex.data.network

import com.x5bartsoft.spacex.model.request.launchdetails.LaunchDetailsRequest
import com.x5bartsoft.spacex.model.request.nextlaunch.NextLaunchRequest
import com.x5bartsoft.spacex.model.request.querylaunches.LaunchesRequest
import com.x5bartsoft.spacex.model.response.launchdetail.LaunchDetail
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.model.response.upcomminglaunch.UpcomingLaunch
import retrofit2.Response
import retrofit2.http.*


interface SpaceXApi {

    @POST("/v4/launches/query")
    suspend fun getAllLaunches(@Body request: LaunchesRequest?): Response<Launches>

    @POST("/v4/launches/query")
    suspend fun getLaunchDetails(@Body detailsRequest: LaunchDetailsRequest?): Response<LaunchDetail>

    @POST("/v4/launches/query")
    suspend fun getNextLaunch(@Body nextLaunch:NextLaunchRequest?): Response<LaunchDetail>
}