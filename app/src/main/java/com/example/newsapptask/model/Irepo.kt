package com.example.newsapptask.model

import kotlinx.coroutines.flow.Flow

interface Irepo {
    fun getTopHeadlinesByCategory(category : String) : Flow<ApiState<NewsResponse>>
}