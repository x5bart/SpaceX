package com.x5bartsoft.spacex.data.database.etities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.x5bartsoft.spacex.model.response.launchdetail.LaunchDetail
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.model.response.upcomminglaunch.UpcomingLaunch
import com.x5bartsoft.spacex.util.Constants.Companion.UPCOMING_LAUNCH_TABLE

@Entity(tableName = UPCOMING_LAUNCH_TABLE)
class NextLaunchEntity (

    var nextLaunch: LaunchDetail,
    ) {
        @PrimaryKey(autoGenerate = false)
        var id = 0
}