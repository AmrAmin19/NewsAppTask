package com.example.newsapptask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapptask.model.ApiState
import com.example.newsapptask.model.Irepo
import com.example.newsapptask.model.NewsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewmodel(val repo : Irepo) : ViewModel() {

    private val _articles = MutableStateFlow<ApiState<NewsResponse>>(ApiState.Loading)
    val articles: StateFlow<ApiState<NewsResponse>> = _articles

    fun getTopHeadlinesByCategory(category : String)
    {
        viewModelScope.launch {
            repo.getTopHeadlinesByCategory(category).collect{
                _articles.value = it
            }
        }
    }

}