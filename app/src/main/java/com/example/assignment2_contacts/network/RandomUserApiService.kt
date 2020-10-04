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

interface RandomUserApiService {
    @GET("api")
    fun getProperties():
            Call<String>
}

object RandomUserApi {
    val retrofitService : RandomUserApiService by lazy {
        retrofit.create(RandomUserApiService::class.java) }
}