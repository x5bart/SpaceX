package com.x5bartsoft.spacex

import com.x5bartsoft.spacex.model.Launch
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val launchesApi: LaunchesApi) {

    suspend fun getAllLaunches(): Response<Launch> {
        return launchesApi.getAllLaunches()
    }
}