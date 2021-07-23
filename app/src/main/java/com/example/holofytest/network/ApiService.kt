package com.example.holofytest.network



import com.example.holofytest.dto.GenericDto
import com.example.holofytest.model.HolofyModel
import com.example.holofytest.model.Videos
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("ec3d996b-e02b-4df7-8840-a5c452a0605a")
    suspend fun getVideos(): Response<GenericDto<HolofyModel?>>


}