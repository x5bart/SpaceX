package com.x5bartsoft.spacex.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.x5bartsoft.spacex.model.launches.Launch

class SpaceXTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun launchesToString(launches: List<Launch>): String {
        return gson.toJson(launches)
    }

    @TypeConverter
    fun stringToLaunches(data: String): List<Launch> {
        val listType = object : TypeToken<List<Launch>>() {}.type
        return gson.fromJson(data, listType)
    }
}