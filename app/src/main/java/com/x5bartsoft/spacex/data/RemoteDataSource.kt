package com.x5bartsoft.spacex.data

import com.x5bartsoft.spacex.data.network.SpaceXApi
import com.x5bartsoft.spacex.model.request.Query
import com.x5bartsoft.spacex.model.request.QueryLaunches
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.model.response.launchpad.Launchpads
import com.x5bartsoft.spacex.model.rockets.Rockets
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val spaceXApi: SpaceXApi) {

    suspend fun getAllLaunches(query: QueryLaunches?): Response<Launches> {
        return spaceXApi.getAllLaunches(query)
    }

    suspend fun getAllRockets(): Response<Rockets> {
        return spaceXApi.getAllRockets()
    }

    suspend fun getAllLaunchpad():Response<Launchpads>{
        return spaceXApi.getAllLaunchpads()
    }


}