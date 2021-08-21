package com.x5bartsoft.spacex

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.x5bartsoft.spacex.data.Lib
import com.x5bartsoft.spacex.data.Lib.rocketsName
import com.x5bartsoft.spacex.data.Repository
import com.x5bartsoft.spacex.model.launches.Launch
import com.x5bartsoft.spacex.model.launchpad.Launchpads
import com.x5bartsoft.spacex.model.rockets.Rockets
import com.x5bartsoft.spacex.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {


    /** RETROFIT */
    var launchesResponse: MutableLiveData<NetworkResult<List<Launch>>> = MutableLiveData()
    var rocketsResponse: MutableLiveData<NetworkResult<Rockets>> = MutableLiveData()
    var launchpadsResponse: MutableLiveData<NetworkResult<Launchpads>> = MutableLiveData()


    fun getLaunches() = viewModelScope.launch {
        getSafeLaunches()
    }

    fun getRockets() = viewModelScope.launch {
        getSafeRockets()
    }

    fun getLaunchpads() = viewModelScope.launch {
        getSafeLaunchpads()
    }



    private suspend fun getSafeLaunches() {
        launchesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getAllLaunches()
                launchesResponse.value = handleLaunchesResponse(response)

//                val launches = launchesResponse.value!!.data
//                if (launches !=null) offLineCache(launches)
            } catch (e: Exception) {
                launchesResponse.value = NetworkResult.Error("Launch not found")
            }
        } else {
            launchesResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private suspend fun getSafeRockets() {
        rocketsResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getAllRockets()
                rocketsResponse.value = handleRocketsResponse(response)

//                val rockets = launchesResponse.value!!.data
//                if (rockets !=null) offLineCache(rockets)
            } catch (e: Exception) {
                rocketsResponse.value = NetworkResult.Error("Rockets not found")
            }
        } else {
            rocketsResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }


    private suspend fun getSafeLaunchpads() {
        launchpadsResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getAllLaunchpad()
                launchpadsResponse.value = handleLaunchpadsResponse(response)

//                val rockets = launchesResponse.value!!.data
//                if (rockets !=null) offLineCache(rockets)
            } catch (e: Exception) {
                launchpadsResponse.value = NetworkResult.Error("Rockets not found")
            }
        } else {
            launchpadsResponse.value = NetworkResult.Error("No Internet Connection")
        }

    }




    private fun handleLaunchesResponse(response: Response<List<Launch>>): NetworkResult<List<Launch>>? {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.body()!!.isNullOrEmpty() -> {
                NetworkResult.Error("No launch found")
            }
            response.isSuccessful -> {
                val launches = response.body()
                NetworkResult.Success(launches!!)
            }
            else -> NetworkResult.Error(response.message())
        }
    }

    private fun handleRocketsResponse(response: Response<Rockets>): NetworkResult<Rockets>? {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.body()!!.isNullOrEmpty() -> {
                NetworkResult.Error("No launch found")
            }
            response.isSuccessful -> {
                val rockets = response.body()
                if (rockets != null)
                    for (i in rockets) {
                        rocketsName[i.id] = i.name
                    }
                NetworkResult.Success(rockets!!)
            }
            else -> NetworkResult.Error(response.message())
        }
    }

    private fun handleLaunchpadsResponse(response: Response<Launchpads>): NetworkResult<Launchpads>? {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            response.body()!!.isNullOrEmpty() -> {
                NetworkResult.Error("No launch found")
            }
            response.isSuccessful -> {
                val rockets = response.body()
                if (rockets != null)
                    for (i in rockets) {
                        Lib.launchpadsName[i.id] = i.name
                    }
                NetworkResult.Success(rockets!!)
            }
            else -> NetworkResult.Error(response.message())
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}