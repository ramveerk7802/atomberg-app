package com.rvcode.atomberg_app.networks


import com.rvcode.atomberg_app.localStorage.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val tokenProvider: TokenProvider): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
            val request = originalRequest.newBuilder()
        tokenProvider.getApiKey()?.let {
            request.addHeader("x-api-key",it)
        }

        tokenProvider.getAccessToken()?.let {
            request.addHeader("Authorization","Bearer $it")
        }
        return chain.proceed(request.build())
    }
}