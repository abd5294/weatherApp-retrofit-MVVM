package com.abdur.weatherforecast.data

import com.abdur.weatherforecast.data.remote.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName : String,
        @Query("appid") apiKey : String,
        @Query("units") units : String
    ) : WeatherInfo

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}