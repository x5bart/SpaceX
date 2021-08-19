package com.x5bartsoft.spacex.data

import com.x5bartsoft.spacex.data.network.LaunchesApi
import com.x5bartsoft.spacex.model.Launch
import com.x5bartsoft.spacex.model.Launches
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val launchesApi: LaunchesApi) {

    suspend fun getAllLaunches(): Response<Launches> {
        return launchesApi.getAllLaunches()
    }
}