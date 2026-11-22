package com.weatherBackend.repository;

import com.weatherBackend.entity.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {

    boolean existsByUser_IdAndCityId(Long userId, Long cityId);

    long countByUser_Id(Long userId);
}
