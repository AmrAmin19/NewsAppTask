package com.example.newsapptask.model.remote

import android.util.Log
import com.example.newsapptask.model.ApiState
import com.example.newsapptask.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteData:Iremote {
    val newsApiService = RetrofitClient.service

    override fun getTopHeadlinesByCategory(category: String): Flow<ApiState<NewsResponse>> = flow {
        emit(ApiState.Loading)
        try {
            val response = newsApiService.getTopHeadlines(category = category)
            emit(ApiState.Success(response))
        } catch (e: Exception) {
            emit(ApiState.Error(e.toString()))
        }
    }
}