package com.weatherBackend.EXTERNAL_API.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrentWeatherResponseDTO {

    private String cityName;
    private String country;
    private double temperatureC;
    private double feelslikeC;
    private int humidity;
    private double windKph;
    private String windDirection;
    private String conditionText;
    private String conditionIcon;

}
