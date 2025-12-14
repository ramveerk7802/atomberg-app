package com.rvcode.atomberg_app.networks

import com.rvcode.atomberg_app.models.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AtombergAuthApi {

    @GET("v1/get_access_token")
    suspend fun getAccessToken( @Header("x-api-key") apiKey: String,
                                @Header("Authorization") authorization: String): Response<TokenResponse>
}