package com.weatherBackend.externalAPI.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherApiCityDTO {

    private Long id;
    private String name;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
    private String url;
}

//CE DTO REPRESENTE LES INFOS RECUES DE L'API LORS DE LA RECHERCHE D'UNE VILLE
