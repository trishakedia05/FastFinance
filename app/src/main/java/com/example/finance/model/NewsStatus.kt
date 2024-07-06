package com.example.finance.model

data class NewsStatus(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)