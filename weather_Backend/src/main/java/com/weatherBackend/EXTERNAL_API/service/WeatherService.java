package com.weatherBackend.EXTERNAL_API.service;

import com.weatherBackend.EXTERNAL_API.DTOs.CityResponseDTO;
import com.weatherBackend.EXTERNAL_API.DTOs.CurrentWeatherResponseDTO;

import java.util.List;

public interface WeatherService {

    List<CityResponseDTO> searchCities(String query);

    CurrentWeatherResponseDTO getCurrentWeather(Long cityId);
}
