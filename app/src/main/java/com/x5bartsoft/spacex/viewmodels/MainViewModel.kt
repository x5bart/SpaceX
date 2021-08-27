package com.x5bartsoft.spacex.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.x5bartsoft.spacex.data.Repository
import com.x5bartsoft.spacex.data.database.etities.LaunchesEntity
import com.x5bartsoft.spacex.model.request.Query
import com.x5bartsoft.spacex.model.request.QueryLaunches
import com.x5bartsoft.spacex.model.response.launches.Launches
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


    fun getLaunches(query: QueryLaunches) = viewModelScope.launch {
        getSafeLaunches(query)
    }


    private suspend fun getSafeLaunches(query: QueryLaunches) {
        launchesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getAllLaunches(query)
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