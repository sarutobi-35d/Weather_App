package com.weatherBackend.repository;

import com.weatherBackend.entity.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {

    boolean existsByUser_IdAndCityId(Long userId, Long cityId);

    long countByUser_Id(Long userId);

    //Consulter ses villes favorites
    List<FavoriteCity> findAllByUser_Id(Long userId);

    Optional<FavoriteCity> findByIdAndUser_Id(Long favoriteId, Long userId);

    boolean existsByUser_IdAndCityIdAndIdNot(Long userId, Long cityId, Long favoriteId);
}
