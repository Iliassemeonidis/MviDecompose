package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.entity.City
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    val favoriteCity: Flow<City>

    fun observeIsFavorite(cityId: Int): Flow<Boolean>

    suspend fun addToFavorite(city: City)

    suspend fun removeFromFavorite(cityId: Int)
}