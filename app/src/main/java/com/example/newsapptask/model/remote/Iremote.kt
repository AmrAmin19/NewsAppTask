package com.example.newsapptask.model.remote

import com.example.newsapptask.model.ApiState
import com.example.newsapptask.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface Iremote {
    fun getTopHeadlinesByCategory (category : String) : Flow<ApiState<NewsResponse>>
}