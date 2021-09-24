package com.x5bartsoft.spacex.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.x5bartsoft.spacex.data.Repository
import com.x5bartsoft.spacex.data.database.etities.FavoriteEntity
import com.x5bartsoft.spacex.data.database.etities.LaunchesEntity
import com.x5bartsoft.spacex.data.database.etities.UpcomingEntity
import com.x5bartsoft.spacex.model.request.launchdetails.LaunchDetailsRequest
import com.x5bartsoft.spacex.model.request.querylaunches.LaunchesRequest
import com.x5bartsoft.spacex.model.response.launchdetail.LaunchDetail
import com.x5bartsoft.spacex.model.response.launches.Launches
import com.x5bartsoft.spacex.model.response.upcomminglaunch.UpcomingLaunch
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
    val readFavoriteLaunch: LiveData<List<FavoriteEntity>> =
        repository.local.readFavoriteLaunches().asLiveData()
    val readUpcomingLaunch: LiveData<List<UpcomingEntity>> = repository.local.readUpcomingLaunch().asLiveData()

    private fun insertLaunches(launchesEntity: LaunchesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertLaunches(launchesEntity)
        }

    fun insertFavoriteLaunch(favoriteEntity: FavoriteEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertFavoriteLaunch(favoriteEntity)
        }

    private fun insertUpcomingLaunch(upcomingLaunch: UpcomingEntity) =
        viewModelScope.launch(Dispatchers.IO){
            repository.local.insertUpcomingLaunch(upcomingLaunch)
        }

    fun deleteFavoriteLaunch(favoriteEntity: FavoriteEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteFavoriteLaunch(favoriteEntity)
        }

    fun deleteAllLauch() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteAllFavoriteLaunch()
        }

    /** RETROFIT */
    var launchesResponse: MutableLiveData<NetworkResult<Launches>> = MutableLiveData()
    var launchesDetailsResponse: MutableLiveData<NetworkResult<LaunchDetail>> = MutableLiveData()
    var upcomingLaunch: MutableLiveData<NetworkResult<UpcomingLaunch>> = MutableLiveData()

    fun getLaunches(request: LaunchesRequest) = viewModelScope.launch {
        getSafeLaunches(request)
    }

    fun getLaunchesDetails(request: LaunchDetailsRequest) = viewModelScope.launch {
        getSafeLaunchesDetails(request)
    }

    fun getUpcomingLaunch() = viewModelScope.launch {
        getSafeUpcomingLaunch()
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
                launchesResponse.value = NetworkResult.Error("Launch not found ")

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
                Log.d("MainViewModel", " exception:$e")
            }
        } else {
            launchesDetailsResponse.value = NetworkResult.Error("No Internet Connection")
        }

    }

    private suspend fun getSafeUpcomingLaunch() {
        upcomingLaunch.value = NetworkResult.Loading()
        val internetConnection = InternetConnection(getApplication())
        if (internetConnection.hasInternetConnection()) {
            try {
                val response = repository.remote.getUpcomingLaunch()
                upcomingLaunch.value = handleUpcomingLaunch(response)

                val launches = upcomingLaunch.value!!.data
                if (launches != null) offLineCacheUpcoming(launches)
            } catch (e: Exception) {
                launchesDetailsResponse.value = NetworkResult.Error("Upcoming launch not found.")
                Log.d("MainViewModel", " exception:$e")
            }
        } else {
            upcomingLaunch.value = NetworkResult.Error("No Internet Connection")
        }
    }


    private fun offLineCache(launches: Launches) {
        val launchesEntity = LaunchesEntity(launches)
        insertLaunches(launchesEntity)

    }

    private fun offLineCacheUpcoming(launches: UpcomingLaunch) {
        val upcomingEntity = UpcomingEntity(launches)
        insertUpcomingLaunch(upcomingEntity)

    }


    private fun handleLaunchesResponse(response: Response<Launches>): NetworkResult<Launches> {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.body()!!.launches.isNullOrEmpty() -> {
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

    private fun handleUpcomingLaunch(response: Response<UpcomingLaunch>): NetworkResult<UpcomingLaunch>? {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.body() == null -> {
                NetworkResult.Error("No upcoming launch found")
            }
            response.isSuccessful -> {
                val upcomingLaunch = response.body()
                NetworkResult.Success(upcomingLaunch!!)
            }
            else -> NetworkResult.Error(response.message())
        }

    }


}