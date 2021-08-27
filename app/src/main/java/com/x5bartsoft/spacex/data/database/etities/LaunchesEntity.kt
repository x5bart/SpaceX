package com.x5bartsoft.spacex.data.database.etities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.util.Constants.Companion.LAUNCHES_TABLE

@Entity(tableName = LAUNCHES_TABLE)
class LaunchesEntity(

    var launches: Launches,
) {
    @PrimaryKey(autoGenerate = false)
    var id = 0
}