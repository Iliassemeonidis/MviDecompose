package com.example.weatherapp.domain.uscase

import com.example.weatherapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class ObserveFavoriteCityUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    operator fun invoke(city: Int) = repository.observeIsFavorite(city)
}