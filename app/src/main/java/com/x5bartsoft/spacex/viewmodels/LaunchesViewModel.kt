package com.x5bartsoft.spacex.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.x5bartsoft.spacex.model.request.*
import com.x5bartsoft.spacex.util.Constants.Companion.DESC
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_LAUNCHPAD
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_ROCKET
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    fun applyQueries(): QueryLaunches {

        val launchpads = listOf(
            "5e9e4501f5090910d4566f83",
            "5e9e4501f509094ba4566f84",
            "5e9e4502f5090927f8566f85",
            "5e9e4502f5090995de566f86",
            "5e9e4502f509092b78566f87",
            "5e9e4502f509094188566f88"
        )

        val rockets = listOf(
            "5e9d0d95eda69955f709d1eb",
            "5e9d0d95eda69973a809d1ec",
            "5e9d0d95eda69974db09d1ed",
            "5e9d0d96eda699382d09d1ee"
        )

        val select = Select()
        val populateLaunchpad = Populate(QUERY_LAUNCHPAD, select)
        val populateRocket = Populate(QUERY_ROCKET, select)
        val listPopulate = listOf(populateLaunchpad, populateRocket)
        val selectX = SelectX()
        val sort = Sort(DESC)
        val options = Options(1000, listPopulate, selectX, sort)
        val query = Query(null, null, null, false)

        return QueryLaunches(options, query)
    }
}