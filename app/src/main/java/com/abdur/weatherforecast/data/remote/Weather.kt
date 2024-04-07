package com.abdur.weatherforecast.data.remote

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)