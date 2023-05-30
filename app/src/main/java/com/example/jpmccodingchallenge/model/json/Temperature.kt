package com.example.jpmccodingchallenge.model.json

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temperature(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
): Parcelable
