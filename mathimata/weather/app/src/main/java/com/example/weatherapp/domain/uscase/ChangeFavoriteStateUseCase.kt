package com.example.weatherapp.domain.uscase

import com.example.weatherapp.domain.entity.City
import com.example.weatherapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class ChangeFavoriteStateUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend fun addToFavoriteCity(city: City) = repository.addToFavorite(city)

    suspend fun removeFromFavorite(cityId: Int) = repository.removeFromFavorite(cityId)
}