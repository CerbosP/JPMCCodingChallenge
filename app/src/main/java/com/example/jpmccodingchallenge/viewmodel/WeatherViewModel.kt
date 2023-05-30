package com.example.jpmccodingchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jpmccodingchallenge.api.WeatherRepository
import com.example.jpmccodingchallenge.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                TAG,
                "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",
                throwable
            )
        }
    }

    private val _weatherList = MutableLiveData<UIState>()
    val weatherList: LiveData<UIState> get() = _weatherList

    fun setWeatherLoading() {_weatherList.value = UIState.Loading}

    fun getWeather(city: String, unit: String) {
        viewModelSafeScope.launch(dispatcher) {
            repository.getWeather(city, unit).collect() {
                _weatherList.postValue(it)
            }
        }
    }

    companion object {
        const val TAG = "WeatherViewModel"
    }
}