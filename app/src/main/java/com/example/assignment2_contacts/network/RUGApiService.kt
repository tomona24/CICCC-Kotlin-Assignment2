package com.example.assignment2_contacts.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://randomuser.me/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RUGApiService {
    @GET("api")
    fun getProperties():
            Call<String>
}

object RUGApi {
    val retrofitService : RUGApiService by lazy {
        retrofit.create(RUGApiService::class.java)
    }
}