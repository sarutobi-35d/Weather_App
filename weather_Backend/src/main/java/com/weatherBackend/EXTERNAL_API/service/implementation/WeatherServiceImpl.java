package com.weatherBackend.EXTERNAL_API.service.implementation;

import com.weatherBackend.EXTERNAL_API.DTOs.CityResponseDTO;
import com.weatherBackend.EXTERNAL_API.client.WeatherApiClient;
import com.weatherBackend.EXTERNAL_API.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherApiClient weatherApiClient;

    @Override
    public List<CityResponseDTO> searchCities(String query) {

        return weatherApiClient.searchCities(query)
                .stream()
                .map(city -> {
                    CityResponseDTO cityResponseDTO = new CityResponseDTO();

                    cityResponseDTO.setId(city.getId());
                    cityResponseDTO.setName(city.getName());
                    cityResponseDTO.setRegion(city.getRegion());
                    cityResponseDTO.setCountry(city.getCountry());
                    cityResponseDTO.setLatitude(city.getLat());
                    cityResponseDTO.setLongitude(city.getLon());

                    return cityResponseDTO;
                })
                .toList();
    }
}
