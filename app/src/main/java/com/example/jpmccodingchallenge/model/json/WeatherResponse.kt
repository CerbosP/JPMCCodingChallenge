package com.example.jpmccodingchallenge.model.json

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherResponse(
    val weather: List<Weather>,
    val main: Temperature,
    val pop: Double
): Parcelable
