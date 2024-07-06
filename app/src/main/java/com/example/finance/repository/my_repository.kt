package com.example.finance.repository

import com.example.finance.data.DataOrException
import com.example.finance.model.NewsStatus
import com.example.finance.network.NewsApi
import javax.inject.Inject

class my_repository @Inject constructor(private val api: NewsApi) {
    suspend fun getNews(country: String, category: String, apiKey: String):
            DataOrException<NewsStatus, Boolean, Exception> {
        val response = try {
            api.getTopHeadlines(country = country, category = category, apiKey = apiKey)
        } catch (e: Exception) {
            // Log.d("REX_myerrorwhattodo","get_data: ${e}")
            return DataOrException(e = e)

        }
        //  Log.d("REX_myerrorwhattodo","get_data: ${response}")
        return DataOrException(data = response)
    }
}