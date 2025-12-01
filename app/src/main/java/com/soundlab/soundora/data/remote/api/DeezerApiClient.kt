package com.soundlab.soundora.data.remote.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DeezerApiClient {

    private const val BASE_URL = "https://api.deezer.com/"

    private val gsonConfig = GsonBuilder().create()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonConfig))
            .build()
    }

    fun build(): DeezerApiService {
        return retrofit.create(DeezerApiService::class.java)
    }
}
