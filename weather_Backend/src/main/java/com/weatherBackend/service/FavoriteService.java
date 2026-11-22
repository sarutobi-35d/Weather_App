package com.weatherBackend.service;

import com.weatherBackend.dto.FavoriteCityRequestDTO;
import com.weatherBackend.dto.FavoriteCityResponseDTO;

public interface FavoriteService {

    FavoriteCityResponseDTO addFavorite(Long userId, FavoriteCityRequestDTO requestDTO);
}
