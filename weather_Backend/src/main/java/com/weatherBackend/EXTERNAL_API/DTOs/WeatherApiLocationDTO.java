package com.weatherBackend.EXTERNAL_API.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherApiLocationDTO {

    private String name;
    private String country;

}
