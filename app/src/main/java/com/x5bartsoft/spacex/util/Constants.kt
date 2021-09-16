package com.x5bartsoft.spacex.util

class Constants {

    companion object {
        const val BASE_URL = "https://api.spacexdata.com"
        const val DESC = "desc"

        const val BUNDLE_LAUNCHES_KEY ="launchesBundle"
        const val BUNDLE_DETAILS_KEY ="detailsBundle"

        val ROCKETS_NAME = mapOf<String, String>(
            "5e9d0d95eda69955f709d1eb" to "Falcon 1",
            "5e9d0d95eda69973a809d1ec" to "Falcon 9",
            "5e9d0d95eda69974db09d1ed" to "Falcon Heavy",
            "5e9d0d96eda699382d09d1ee" to "Starship"
        )
        val ROCKETS_ID = mapOf<String, String>(
            "Falcon 1" to "5e9d0d95eda69955f709d1eb",
            "Falcon 9" to "5e9d0d95eda69973a809d1ec",
            "Falcon Heavy" to "5e9d0d95eda69974db09d1ed",
            "Starship" to "5e9d0d96eda699382d09d1ee"
        )

        val LAUNCHPADS_ID = mapOf(
            "VAFB SLC 3W" to "5e9e4501f5090910d4566f83",
            "CCSFS SLC 40" to "5e9e4501f509094ba4566f84",
            "STLS" to "5e9e4502f5090927f8566f85",
            "Kwajalein Atoll" to "5e9e4502f5090995de566f86",
            "VAFB SLC 4E" to "5e9e4502f509092b78566f87",
            "KSC LC 39A" to "5e9e4502f509094188566f88"
        )

        //ROOM Database
        const val DATABASE_NAME = "space_x_database"
        const val LAUNCHES_TABLE = "launches_table"

        //Query
        const val QUERY_LAUNCHPAD = "launchpad"
        const val QUERY_LAUNCHES = "launches"
        const val QUERY_ROCKET = "rocket"
        const val QUERY_ROCKETS = "rockets"
        const val QUERY_PAYLOADS = "payloads"
        const val QUERY_CORES = "cores"
        const val QUERY_CORE = "core"
        const val QUERY_LANDPAD = "landpad"
        const val QUERY_SHIPS = "ships"
        const val QUERY_CAPSULES = "capsules"

        //Bottom Sheet and Preferences
        val DEFAULT_ROCKETS:MutableSet<String> = mutableSetOf()
        val DEFAULT_LAUNCHPAD:MutableSet<String> = mutableSetOf()
        val DEFAULT_SUCCESS:String? = null
        const val DEFAULT_UPCOMING = false
        const val DEFAULT_LIMIT = 1000

        const val PREFERENCES_NAME = "launches_preferences"
        const val PREFERENCES_ROCKET = "rocket"
        const val PREFERENCES_ROCKET_ID = "rocketId"
        const val PREFERENCES_LAUNCHPAD = "launchpad"
        const val PREFERENCES_LAUNCHPAD_ID = "launchpadId"
        const val PREFERENCES_SUCCESS = "success"
        const val PREFERENCES_SUCCESS_ID = "successId"
        const val PREFERENCES_BACK_ONLINE = "backOnline"

    }
}