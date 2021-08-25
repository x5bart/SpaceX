package com.x5bartsoft.spacex.data

import com.x5bartsoft.spacex.data.database.SpaceXDao
import com.x5bartsoft.spacex.data.database.etities.LaunchesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val spaceXDao: SpaceXDao
){

    fun readLaunches():Flow<List<LaunchesEntity>>{
        return spaceXDao.readLaunches()
    }

    suspend fun insertLaunches(launchesEntity: LaunchesEntity){
        spaceXDao.insertLaunches(launchesEntity)
    }
}