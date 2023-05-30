package com.example.jpmccodingchallenge.model.json

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val main: String,
    val description: String,
    val icon: String
): Parcelable
