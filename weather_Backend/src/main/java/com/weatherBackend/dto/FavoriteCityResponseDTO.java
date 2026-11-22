package com.weatherBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavoriteCityResponseDTO {

    private Long id;
    private Long cityId;
    private String cityName;
    private String country;
}
