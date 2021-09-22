package com.x5bartsoft.spacex.data

import com.x5bartsoft.spacex.data.network.SpaceXApi
import com.x5bartsoft.spacex.model.request.launchdetails.LaunchDetailsRequest
import com.x5bartsoft.spacex.model.request.querylaunches.LaunchesRequest
import com.x5bartsoft.spacex.model.response.launchdetail.LaunchDetail
import com.x5bartsoft.spacex.model.response.launches.Launches
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val spaceXApi: SpaceXApi) {

    suspend fun getAllLaunches(request: LaunchesRequest?): Response<Launches> {
        return spaceXApi.getAllLaunches(request)
    }

    suspend fun getLaunchesDetail(detailsRequest: LaunchDetailsRequest?): Response<LaunchDetail> {
        return spaceXApi.getLaunchDetails(detailsRequest)
    }




}