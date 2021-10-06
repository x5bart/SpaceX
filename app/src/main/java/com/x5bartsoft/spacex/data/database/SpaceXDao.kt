package com.x5bartsoft.spacex.data.database

import androidx.room.*
import com.x5bartsoft.spacex.data.database.etities.FavoriteEntity
import com.x5bartsoft.spacex.data.database.etities.LaunchesEntity
import com.x5bartsoft.spacex.data.database.etities.NextLaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceXDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launchesEntity: LaunchesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteLaunch(favoriteLaunch:FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNextLaunch(nextLaunchLaunch:NextLaunchEntity)

    @Query("SELECT * FROM launches_table ORDER BY id ASC")
    fun readLaunches(): Flow<List<LaunchesEntity>>

    @Query("SELECT * FROM launches_favorite_table ORDER BY id ASC")
    fun readFavoriteLaunch():Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM upcoming_launch_table ORDER BY id ASC")
    fun readNextLaunch():Flow<List<NextLaunchEntity>>

    @Delete
    suspend fun deleteFavoriteLaunch(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM launches_favorite_table")
    suspend fun deleteAllFavoriteLaunch()
}