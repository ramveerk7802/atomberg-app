package com.rvcode.atomberg_app.localStorage

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(context: Context) {

    private val appContext = context.applicationContext
    suspend fun saveApiKey(apiKey: String,accessToken: String){
        appContext.dataStore.edit {pref->
            pref[PreferencesKeys.API_KEY]=apiKey
            pref[PreferencesKeys.ACCESS_TOKEN]=accessToken
        }
    }

    // read api key
    val apiKey : Flow<String?> = appContext.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.API_KEY]
    }

    // read access token

    val accessToken : Flow<String?> = appContext.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.ACCESS_TOKEN]
    }

}