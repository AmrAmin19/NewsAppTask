package com.example.newsapptask.model.remote

import com.example.newsapptask.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
//
//    @GET("v2/top-headlines")
//    suspend fun getTopHeadlines(
//        @Query("country") country: String = "us",
//        @Query("category") category: String = "general",
//        @Query("apikey") apiKey: String = "dd15c6d1ca5a4706882449168fc4479a"
//    ): NewsResponse


    @GET("v4/top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String = "general",
        @Query("lang") country: String = "en",
        @Query("apikey") apiKey: String = "9222878f43b442f46e9d34e02ff4695d"
    ): NewsResponse
}