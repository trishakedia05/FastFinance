package com.example.finance.screens.main

import androidx.lifecycle.ViewModel
import com.example.finance.data.DataOrException
import com.example.finance.model.NewsStatus
import com.example.finance.repository.my_repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository:my_repository):ViewModel()
{
    suspend fun getAllNews(country:String,category:String,apiKey:String)
    : DataOrException<NewsStatus,Boolean,Exception>{
        return repository.getNews(country,category,apiKey)
    }




//    val data: MutableState<DataOrException<News_status,Boolean,Exception>>
//    = mutableStateOf(DataOrException(null,true,Exception("")))
//
//init{
//    loadNews()
//}
//private fun loadNews(){
//    getNews("in","business",constants.apiKey)
//}
//
//private fun getNews(country:String,category:String,apiKey:String){
//    viewModelScope.launch{
//        data.value.loading=true
//        data.value=repository.getNews(country,category,apiKey)
//        if (data.value.data.toString().isNotEmpty()){
//            data.value.loading=false
//        }
//    Log.d("rex_what_to_do","myNEWS: ${data.value.data?.totalResults.toString()}")
//    }}

//    private val _newsData = MutableStateFlow<DataOrException<News_status, Boolean, Exception>>(
//        DataOrException(loading = true)
//    )
//
//    val newsData: StateFlow<DataOrException<News_status, Boolean, Exception>> = _newsData
//
//    fun fetchNews() {
//        viewModelScope.launch {
//            _newsData.value = repository.getNews("in", "business", constants.apiKey)
//        }
//    }
}
