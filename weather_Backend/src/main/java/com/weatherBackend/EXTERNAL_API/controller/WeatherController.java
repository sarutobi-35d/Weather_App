package com.weatherBackend.EXTERNAL_API.controller;

import com.weatherBackend.EXTERNAL_API.DTOs.CityResponseDTO;
import com.weatherBackend.EXTERNAL_API.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/search")
    public ResponseEntity<List<CityResponseDTO>> searchCity(@RequestParam String query){

        List<CityResponseDTO> cityResponseDTOS = weatherService.searchCities(query);

        return ResponseEntity.ok(cityResponseDTOS);
    }
}
