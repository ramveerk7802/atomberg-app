package com.rvcode.atomberg_app.networks

import com.rvcode.atomberg_app.localStorage.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()

        // Fetch tokens (This will now block briefly to get the actual value)
        val apiKey = tokenProvider.getApiKey()
        val token = tokenProvider.getAccessToken()

        if (!apiKey.isNullOrBlank()) {
            requestBuilder.addHeader("x-api-key", apiKey)
        }

        if (!token.isNullOrBlank()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}