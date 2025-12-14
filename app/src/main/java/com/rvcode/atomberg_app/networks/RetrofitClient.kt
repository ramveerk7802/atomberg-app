package com.rvcode.atomberg_app.networks

import android.app.Application
import com.rvcode.atomberg_app.localStorage.DataStoreManager
import com.rvcode.atomberg_app.localStorage.DataStoreTokenProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private lateinit var tokenProvider: DataStoreTokenProvider

    fun init(application: Application){
        val dataStoreManager =
            DataStoreManager(application.applicationContext)

        tokenProvider = DataStoreTokenProvider(dataStoreManager)
    }


    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(tokenProvider = tokenProvider))
            .build()
    }


    private val retrofitWithoutHttp: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApi: AtombergAuthApi by lazy {
        retrofitWithoutHttp.create(AtombergAuthApi::class.java)
    }

    val atombergApi : AtombergApi by lazy {
        retrofit.create(AtombergApi::class.java)
    }
}