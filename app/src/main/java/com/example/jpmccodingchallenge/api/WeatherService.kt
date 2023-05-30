package com.example.jpmccodingchallenge.api

import com.example.jpmccodingchallenge.model.json.WeatherResponse
import com.example.jpmccodingchallenge.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String = "Atlanta",
        @Query("appid") key: String = API_KEY,
        @Query("units") unit: String = "standard"
    ): Response<WeatherResponse>
}