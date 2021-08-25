package com.x5bartsoft.spacex.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.x5bartsoft.spacex.data.database.etities.LaunchesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceXDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launchesEntity: LaunchesEntity)

    @Query("SELECT * FROM launches_table ORDER BY id ASC")
    fun readLaunches(): Flow<List<LaunchesEntity>>
}