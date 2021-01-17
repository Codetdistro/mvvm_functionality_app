package com.example.movieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class viewModel(private val repository: Repository):ViewModel() {

    val apiResult = MutableLiveData<Resource<ApiResult>>()

    init {
        fetchData()
    }

     fun fetchData() {
        CoroutineScope(Dispatchers.Default).launch {
            apiResult.postValue(Resource.loading(null))
            try {
                val datFromApideferred =  repository.getRepositoryResult()

                apiResult.postValue(Resource.success(datFromApideferred.body()))

            }catch (e:Exception)
            {
                apiResult.postValue(Resource.error("error aa gyi h",null))
            }
        }
    }
}