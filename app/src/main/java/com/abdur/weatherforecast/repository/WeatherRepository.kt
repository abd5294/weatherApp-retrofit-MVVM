package com.abdur.weatherforecast.repository

import com.abdur.weatherforecast.data.remote.WeatherInfo

interface WeatherRepository {

    suspend fun getWeather(location : String) : WeatherInfo?
}