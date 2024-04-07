package com.abdur.weatherforecast.ui.weatherscreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdur.weatherforecast.data.remote.WeatherInfo
import com.abdur.weatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    private var _query = mutableStateOf("")
    var query = _query

    private var job: Job? = null

    private var _weatherState = mutableStateOf<WeatherInfo?>(null)
    var weatherState = _weatherState

    fun onSearch(location: String) {
        _query.value = location
        job?.cancel()
        job = viewModelScope.launch {
            delay(500L)
            val response = repository.getWeather(location)
            _weatherState.value = response
        }
    }
}
