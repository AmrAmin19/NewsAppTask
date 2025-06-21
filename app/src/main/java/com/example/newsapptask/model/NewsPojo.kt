package com.example.newsapptask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//data class NewsResponse(
//    val status: String,
//    val totalResults: Int,
//    val articles: List<Article>
//)
//
//data class Article(
//    val source: Source,
//    val author: String?,
//    val title: String,
//    val description: String?,
//    val url: String,
//    val urlToImage: String?,
//    val publishedAt: String,
//    val content: String?
//)
//
//data class Source(
//    val id: String?,
//    val name: String
//)

data class NewsResponse(
    val totalArticles: Int,
    val articles: List<Article>
)

@Parcelize
data class Article(
    val title: String,
    val description: String?,
    val content: String?,
    val url: String,
    val image: String?,
    val publishedAt: String,
    val source: Source
): Parcelable

@Parcelize
data class Source(
    val name: String,
    val url: String
): Parcelable
