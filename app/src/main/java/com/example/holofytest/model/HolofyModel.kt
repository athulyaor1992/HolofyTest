package com.example.holofytest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HolofyModel(

    val name: String,
    val videos: List<Videos>

): Parcelable

/*
@Parcelize
data class HolofyModel(

    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String

): Parcelable
*/

