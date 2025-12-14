package com.rvcode.atomberg_app.networks

import android.app.Application
import com.rvcode.atomberg_app.localStorage.DataStoreManager
import com.rvcode.atomberg_app.localStorage.DataStoreTokenProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private lateinit var tokenProvider: DataStoreTokenProvider

    fun init(application: Application) {
        val dataStoreManager = DataStoreManager(application)
        tokenProvider = DataStoreTokenProvider(dataStoreManager)
    }

    // 1. Client WITH Interceptor (Authenticated calls)
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(tokenProvider))
            .build()
    }

    // 2. Retrofit WITH Interceptor
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // 3. Retrofit WITHOUT Interceptor (For Login/Auth calls)
    private val retrofitNoAuth: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val atombergApi: AtombergApi by lazy {
        retrofit.create(AtombergApi::class.java)
    }

    val authApi: AtombergAuthApi by lazy {
        retrofitNoAuth.create(AtombergAuthApi::class.java)
    }
}