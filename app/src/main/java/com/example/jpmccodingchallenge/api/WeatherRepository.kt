package com.example.jpmccodingchallenge.api

import com.example.jpmccodingchallenge.model.UIState
import com.example.jpmccodingchallenge.utils.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface WeatherRepository {
    suspend fun getWeather(city: String, unit: String): Flow<UIState>
}

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherService
): WeatherRepository {
    override suspend fun getWeather(city: String, unit: String): Flow<UIState> =
        flow {
            try {
                val response = weatherApi.getWeather(city, API_KEY, unit)
                if (response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Null Response"))
                } else {
                    throw Exception("Failed Network Call")
                }
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }
}