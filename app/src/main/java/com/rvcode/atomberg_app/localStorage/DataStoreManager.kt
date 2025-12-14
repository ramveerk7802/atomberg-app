package com.rvcode.atomberg_app.localStorage

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Define the extension property at the top level
val Context.dataStore by preferencesDataStore(name = "app_settings")

class DataStoreManager(private val application: Application) {

    // Save Data
    val appContext = application.applicationContext
    suspend fun saveApiKey(apiKey: String, accessToken: String) {
        appContext.dataStore.edit { pref ->
            pref[PreferencesKeys.API_KEY] = apiKey
            pref[PreferencesKeys.ACCESS_TOKEN] = accessToken
        }
    }

    // Get Data Once (Suspend functions)
    suspend fun getApiKeyOnce(): String? {
        return appContext.dataStore.data.map { it[PreferencesKeys.API_KEY] }.first()
    }

    suspend fun getAccessTokenOnce(): String? {
        return appContext.dataStore.data.map { it[PreferencesKeys.ACCESS_TOKEN] }.first()
    }
}