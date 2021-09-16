package com.x5bartsoft.spacex.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.x5bartsoft.spacex.data.Repository
import com.x5bartsoft.spacex.data.database.etities.LaunchesEntity
import com.x5bartsoft.spacex.model.request.launchdetails.LaunchDetailsRequest
import com.x5bartsoft.spacex.model.request.querylaunches.LaunchesRequest
import com.x5bartsoft.spacex.model.response.launchdetail.LaunchDetail
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.util.InternetConnection
import com.x5bartsoft.spacex.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    /** ROOM DATABASE */

    val readLaunches: LiveData<List<LaunchesEntity>> = repository.local.readLaunches().asLiveData()

    private fun insertLaunches(launchesEntity: LaunchesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertLaunches(launchesEntity)
        }

    /** RETROFIT */
    var launchesResponse: MutableLiveData<NetworkResult<Launches>> = MutableLiveData()
    var launchesDetailsResponse: MutableLiveData<NetworkResult<LaunchDetail>> = MutableLiveData()

    fun getLaunches(request: LaunchesRequest) = viewModelScope.launch {
        getSafeLaunches(request)
    }

    fun getLaunchesDetails(request: LaunchDetailsRequest) = viewModelScope.launch {
        getSafeLaunchesDetails(request)
    }


    private suspend fun getSafeLaunches(request: LaunchesRequest) {
        launchesResponse.value = NetworkResult.Loading()
        val internetConnection = InternetConnection(getApplication())
        if (internetConnection.hasInternetConnection()) {
            try {
                val response = repository.remote.getAllLaunches(request)
                launchesResponse.value = handleLaunchesResponse(response)

                val launches = launchesResponse.value!!.data
                if (launches != null) offLineCache(launches)
            } catch (e: Exception) {
                launchesResponse.value = NetworkResult.Error("Launch not found")
            }
        } else {
            launchesResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private suspend fun getSafeLaunchesDetails(request: LaunchDetailsRequest) {
        launchesDetailsResponse.value = NetworkResult.Loading()
        val internetConnection = InternetConnection(getApplication())
        if (internetConnection.hasInternetConnection()) {
            try {
                val response = repository.remote.getLaunchesDetail(request)
                launchesDetailsResponse.value = handleLaunchDetailsResponse(response)
            } catch (e: Exception) {
                launchesDetailsResponse.value = NetworkResult.Error("Launch detail not found.")
            }
        } else {
            launchesDetailsResponse.value = NetworkResult.Error("No Internet Connection")
        }

    }



    private fun offLineCache(launches: Launches) {
        val launchesEntity = LaunchesEntity(launches)
        insertLaunches(launchesEntity)

    }


    private fun handleLaunchesResponse(response: Response<Launches>): NetworkResult<Launches> {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.body()!!.docs.isNullOrEmpty() -> {
                NetworkResult.Error("No launch found")
            }
            response.isSuccessful -> {
                val launches = response.body()
                NetworkResult.Success(launches!!)
            }
            else -> NetworkResult.Error(response.message())
        }
    }

    private fun handleLaunchDetailsResponse(response: Response<LaunchDetail>): NetworkResult<LaunchDetail>? {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.body()!!.docs.isNullOrEmpty() -> {
                NetworkResult.Error("No launch details found")
            }
            response.isSuccessful -> {
                val launchDetail = response.body()
                NetworkResult.Success(launchDetail!!)
            }
            else -> NetworkResult.Error(response.message())
        }
    }




}