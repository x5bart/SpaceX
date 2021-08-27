package com.x5bartsoft.spacex.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.x5bartsoft.spacex.model.response.launches.Launches

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
}