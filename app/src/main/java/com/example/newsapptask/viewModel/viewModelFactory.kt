package com.example.newsapptask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapptask.model.Irepo

class HomeFactory(private val repo: Irepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewmodel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class CategoryFactory(private val repo: Irepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewmodel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

