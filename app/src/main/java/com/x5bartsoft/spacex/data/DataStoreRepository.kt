package com.x5bartsoft.spacex.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.x5bartsoft.spacex.util.Constants.Companion.DEFAULT_LAUNCHPAD
import com.x5bartsoft.spacex.util.Constants.Companion.DEFAULT_ROCKETS
import com.x5bartsoft.spacex.util.Constants.Companion.DEFAULT_SUCCESS
import com.x5bartsoft.spacex.util.Constants.Companion.PREFERENCES_BACK_ONLINE
import com.x5bartsoft.spacex.util.Constants.Companion.PREFERENCES_LAUNCHPAD
import com.x5bartsoft.spacex.util.Constants.Companion.PREFERENCES_LAUNCHPAD_ID
import com.x5bartsoft.spacex.util.Constants.Companion.PREFERENCES_NAME
import com.x5bartsoft.spacex.util.Constants.Companion.PREFERENCES_ROCKET
import com.x5bartsoft.spacex.util.Constants.Companion.PREFERENCES_ROCKET_ID
import com.x5bartsoft.spacex.util.Constants.Companion.PREFERENCES_SUCCESS
import com.x5bartsoft.spacex.util.Constants.Companion.PREFERENCES_SUCCESS_ID
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val selectedRockets = stringSetPreferencesKey(PREFERENCES_ROCKET)
        val selectedRocketId = stringSetPreferencesKey(PREFERENCES_ROCKET_ID)
        val selectedLaunchpads = stringSetPreferencesKey(PREFERENCES_LAUNCHPAD)
        val selectedLaunchpadsId = stringSetPreferencesKey(PREFERENCES_LAUNCHPAD_ID)
        val selectedSuccess = stringPreferencesKey(PREFERENCES_SUCCESS)
        val selectedSuccessId = intPreferencesKey(PREFERENCES_SUCCESS_ID)
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)
    }


    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun saveQueryFilter(
        rockets: Set<String>,
        rocketsId: Set<String>,
        launchpads: Set<String>,
        launchpadsId: Set<String>,
        success: String,
        successId: Int,

        ) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedRockets] = rockets
            preferences[PreferenceKeys.selectedRocketId] = rocketsId
            preferences[PreferenceKeys.selectedLaunchpads] = launchpads
            preferences[PreferenceKeys.selectedLaunchpadsId] = launchpadsId
            preferences[PreferenceKeys.selectedSuccess] = success
            preferences[PreferenceKeys.selectedSuccessId] = successId

        }
    }

    suspend fun saveBackOnline(backOnline: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline
        }
    }

    val readQueryFilter: Flow<QueryFilter> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw  exception
            }
        }
        .map { preferences ->
            val selectedRockets = preferences[PreferenceKeys.selectedRockets] ?: DEFAULT_ROCKETS
            val selectedRocketsId = preferences[PreferenceKeys.selectedRocketId] ?: setOf()
            val selectedLaunchpads =
                preferences[PreferenceKeys.selectedLaunchpads] ?: DEFAULT_LAUNCHPAD
            val selectedLaunchpadsId = preferences[PreferenceKeys.selectedLaunchpadsId] ?: setOf()
            val selectedSuccess = preferences[PreferenceKeys.selectedSuccess] ?: DEFAULT_SUCCESS
            val selectedSuccessId = preferences[PreferenceKeys.selectedSuccessId] ?: 1
            QueryFilter(
                selectedRockets,
                selectedRocketsId,
                selectedLaunchpads,
                selectedLaunchpadsId,
                selectedSuccess,
                selectedSuccessId
            )
        }


    val readBeckOnline: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw  exception
            }
        }
        .map { preferences ->
            val backOnline = preferences[PreferenceKeys.backOnline] ?: false
            backOnline
        }

}

data class QueryFilter(
    val selectedRockets: Set<String>?,
    val selectedRocketsId: Set<String>,
    val selectedLaunchpads: Set<String>?,
    val selectedLaunchpadsId: Set<String>,
    val selectedSuccess: String?,
    val selectedSuccessId: Int,
)