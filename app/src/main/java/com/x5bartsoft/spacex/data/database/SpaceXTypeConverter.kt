package com.x5bartsoft.spacex.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.x5bartsoft.spacex.model.response.launches.Doc
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.model.response.upcomminglaunch.UpcomingLaunch

class SpaceXTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun launchesToString(launches: Launches): String {
        return gson.toJson(launches)
    }

    @TypeConverter
    fun stringToLaunches(data: String): Launches {
        val listType = object : TypeToken<Launches>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun launchToString(launch: Doc): String {
        return gson.toJson(launch)
    }

    @TypeConverter
    fun stringToResult(data: String): Doc {
        val listType = object : TypeToken<Doc>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun upcomingLaunchToString(upcomingLaunch: UpcomingLaunch): String {
        return gson.toJson(upcomingLaunch)
    }

    @TypeConverter
    fun stringToupcomingLaunch(data: String): UpcomingLaunch {
        val listType = object : TypeToken<UpcomingLaunch>() {}.type
        return gson.fromJson(data, listType)
    }
}