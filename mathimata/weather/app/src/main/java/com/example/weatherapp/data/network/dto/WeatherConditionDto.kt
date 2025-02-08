package com.example.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherConditionDto(
    @SerializedName("current") val weatherDto: WeatherDto
)