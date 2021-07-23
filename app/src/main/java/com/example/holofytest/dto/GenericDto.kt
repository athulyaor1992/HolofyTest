package com.example.holofytest.dto

import com.example.holofytest.model.HolofyModel
import com.example.holofytest.model.Videos
import com.google.gson.annotations.SerializedName

data class GenericDto<T>(
    @SerializedName("categories") val result: MutableList<HolofyModel>
)

