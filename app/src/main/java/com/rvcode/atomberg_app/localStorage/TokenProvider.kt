package com.rvcode.atomberg_app.localStorage

interface TokenProvider {
    fun getApiKey(): String?
    fun getAccessToken(): String?
}