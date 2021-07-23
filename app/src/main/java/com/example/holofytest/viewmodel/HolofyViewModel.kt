package com.example.holofytest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.holofytest.App
import com.example.holofytest.network.ApiService
import com.example.holofytest.util.Resource
import kotlinx.coroutines.Dispatchers

class HolofyViewModel(val apiService: ApiService = App.api!!
): ViewModel(){
    fun getVideos() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getVideos()))
        } catch (exception: Exception) {
            Log.e("Error",exception.toString());
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
