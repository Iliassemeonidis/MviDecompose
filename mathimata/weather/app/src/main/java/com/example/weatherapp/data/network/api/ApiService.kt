package com.example.weatherapp.data.network.api

import com.example.weatherapp.data.network.dto.CityDto
import com.example.weatherapp.data.network.dto.WeatherConditionDto
import com.example.weatherapp.data.network.dto.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
    suspend fun loadCurrentWeather(
        @Query("q") query: String
    ): WeatherConditionDto

    @GET("forecast.json")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") daysCount: Int = 4,
    ): WeatherForecastDto

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String,
    ) : List<CityDto>
}