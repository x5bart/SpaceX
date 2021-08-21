package com.x5bartsoft.spacex.data

import com.x5bartsoft.spacex.data.network.SpaceXApi
import com.x5bartsoft.spacex.model.launches.Launch
import com.x5bartsoft.spacex.model.launchpad.Launchpads
import com.x5bartsoft.spacex.model.rockets.Rockets
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val spaceXApi: SpaceXApi) {

    suspend fun getAllLaunches(): Response<List<Launch>> {
        return spaceXApi.getAllLaunches()
    }

    suspend fun getAllRockets(): Response<Rockets> {
        return spaceXApi.getAllRockets()
    }

    suspend fun getAllLaunchpad():Response<Launchpads>{
        return spaceXApi.getAllLaunchpads()
    }


}