package com.example.weatherapp.domain.entity

data class Forecast(
    val currentForecast: Weather,
    val upcomingForecast: List<Weather>
)