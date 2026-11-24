package com.weatherBackend.EXTERNAL_API.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherApiCurrentDTO {

    private WeatherApiLocationDTO locationDTO;

    private WeatherApiCurrentDataDTO currentDataDTO;
}

//CA, C'EST LE DTO PRINCIPAL QUI CONTIENT LA LISTE DES OBJETS DE LA REPONSE JSON
