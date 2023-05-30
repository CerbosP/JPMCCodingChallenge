package com.example.jpmccodingchallenge.di

import com.example.jpmccodingchallenge.api.WeatherRepository
import com.example.jpmccodingchallenge.api.WeatherRepositoryImpl
import com.example.jpmccodingchallenge.api.WeatherService
import com.example.jpmccodingchallenge.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {
    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).client(
                provideOkHttpClient()
            ).build()
            .create(WeatherService::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRepository(): WeatherRepository = WeatherRepositoryImpl(provideWeatherApi())

    @Singleton
    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}