package com.abdur.weatherforecast.repository

import com.abdur.weatherforecast.data.WeatherApi
import com.abdur.weatherforecast.data.remote.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi : WeatherApi
) : WeatherRepository {
    override suspend fun getWeather(location: String): WeatherInfo? {
        val weatherInfo = try {
            weatherApi.getWeather(location, "a51cc7bedcd83e40caa8a2467e0fcb31", "metric")
        } catch (e : Exception){
            null
        }
        return weatherInfo
    }
}