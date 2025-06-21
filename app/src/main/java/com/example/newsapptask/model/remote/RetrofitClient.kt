package com.example.newsapptask.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
   // private const val BASE_URL = "https://newsapi.org/"

    private const val BASE_URL = "https://gnews.io/api/"

    val retrofitInstance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofitInstance.create(NewsApiService::class.java)
}