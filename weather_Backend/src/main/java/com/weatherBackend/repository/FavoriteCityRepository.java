package com.weatherBackend.repository;

import com.weatherBackend.entity.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {

}
