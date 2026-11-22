package com.weatherBackend.service;

import com.weatherBackend.dto.FavoriteCityRequestDTO;
import com.weatherBackend.dto.FavoriteCityResponseDTO;
import com.weatherBackend.entity.FavoriteCity;

import java.util.List;

public interface FavoriteService {

    FavoriteCityResponseDTO addFavorite(Long userId, FavoriteCityRequestDTO requestDTO);

    List<FavoriteCityResponseDTO> getFavorites(Long userId);

    void deleteFavorite(Long favoriteId, Long userId);
}
