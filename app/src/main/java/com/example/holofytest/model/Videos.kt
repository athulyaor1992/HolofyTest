package com.example.holofytest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Videos(

    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String

): Parcelable

