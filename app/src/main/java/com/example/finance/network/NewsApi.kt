package com.example.finance.network

import com.example.finance.model.NewsStatus
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

        @GET("top-headlines")
        suspend fun getTopHeadlines(
            @Query("country") country: String,
            @Query("category") category: String,
            @Query("apiKey") apiKey: String
        ): NewsStatus

    }

