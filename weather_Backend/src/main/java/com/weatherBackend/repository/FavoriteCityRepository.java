package com.weatherBackend.repository;

import com.weatherBackend.entity.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {

    boolean existsByUser_IdAndCityId(Long userId, Long cityId);

    long countByUser_Id(Long userId);

    //Consulter ses villes favorites
    List<FavoriteCity> findAllByUser_Id(Long userId);

}
