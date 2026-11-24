package com.weatherBackend.EXTERNAL_API.service;

import com.weatherBackend.EXTERNAL_API.DTOs.CityResponseDTO;

import java.util.List;

public interface WeatherService {

    List<CityResponseDTO> searchCities(String query);
}
