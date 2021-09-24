package com.x5bartsoft.spacex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.x5bartsoft.spacex.data.database.etities.FavoriteEntity
import com.x5bartsoft.spacex.data.database.etities.LaunchesEntity
import com.x5bartsoft.spacex.data.database.etities.UpcomingEntity

@Database(
    entities = [LaunchesEntity::class,FavoriteEntity::class,UpcomingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SpaceXTypeConverter::class)
abstract class SpaceXDatabase : RoomDatabase() {
    abstract fun spaceXDao(): SpaceXDao
}