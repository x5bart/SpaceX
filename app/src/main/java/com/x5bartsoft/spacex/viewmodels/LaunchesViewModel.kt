package com.x5bartsoft.spacex.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.x5bartsoft.spacex.data.DataStoreRepository
import com.x5bartsoft.spacex.model.request.querylaunches.Options
import com.x5bartsoft.spacex.model.request.querylaunches.Populate
import com.x5bartsoft.spacex.model.request.querylaunches.Query
import com.x5bartsoft.spacex.model.request.querylaunches.LaunchesRequest
import com.x5bartsoft.spacex.util.Constants.Companion.DEFAULT_LAUNCHPAD
import com.x5bartsoft.spacex.util.Constants.Companion.DEFAULT_ROCKETS
import com.x5bartsoft.spacex.util.Constants.Companion.DEFAULT_SUCCESS
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_LAUNCHPAD
import com.x5bartsoft.spacex.util.Constants.Companion.QUERY_ROCKET
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository,
) : AndroidViewModel(application) {

    val readQueryFilter = dataStoreRepository.readQueryFilter
    val readBeckOnline = dataStoreRepository.readBeckOnline.asLiveData()

    var networkStatus = false
    var backOnline = false

    fun saveQueryFilter(
        rockets: Set<String>,
        rocketsId: Set<String>,
        launchpads: Set<String>,
        launchpadsId: Set<String>,
        success: String,
        successId: Int,
    ) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveQueryFilter(rockets,
            rocketsId,
            launchpads,
            launchpadsId,
            success,
            successId)
    }

    private fun saveBackOnline(backOnline: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }

    private var rockets: MutableSet<String>? = DEFAULT_ROCKETS
    private var launchpad: MutableSet<String>? = DEFAULT_LAUNCHPAD
    private var success: String? = DEFAULT_SUCCESS

    fun applyQueries(): LaunchesRequest {
        viewModelScope.launch {
            readQueryFilter.collect { value ->
                rockets =
                    if (!value.selectedRockets.isNullOrEmpty()) value.selectedRockets.toMutableSet()
                    else null
                launchpad =
                    if (!value.selectedLaunchpads.isNullOrEmpty()) value.selectedLaunchpads.toMutableSet()
                    else null
                success = value.selectedSuccess
            }
        }

        val populateLaunchpad = Populate(QUERY_LAUNCHPAD)
        val populateRocket = Populate(QUERY_ROCKET)
        val listPopulate = listOf(populateLaunchpad, populateRocket)
        val options = Options(listPopulate)
        val launchpadQuery = if (launchpad != null && launchpad!!.size == 0) null else launchpad
        val rocketQuery = if (rockets != null && rockets!!.size == 0) null else rockets
        val successQuery = if (success == "null") null else success
        val query = Query(launchpadQuery, rocketQuery, successQuery)

        return LaunchesRequest(options, query)
    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }
}