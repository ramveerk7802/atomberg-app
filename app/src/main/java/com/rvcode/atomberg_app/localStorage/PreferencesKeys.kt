package com.rvcode.atomberg_app.localStorage

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val API_KEY = stringPreferencesKey("api_key")
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
}