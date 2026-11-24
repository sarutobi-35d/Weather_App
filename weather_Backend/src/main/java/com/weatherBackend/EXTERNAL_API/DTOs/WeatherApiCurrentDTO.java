package com.weatherBackend.EXTERNAL_API.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherApiCurrentDTO {

    @JsonProperty("location")
    private WeatherApiLocationDTO locationDTO;

    @JsonProperty("current")
    private WeatherApiCurrentDataDTO currentDataDTO;
}

//CA, C'EST LE DTO PRINCIPAL QUI CONTIENT LES OBJETS DE LA REPONSE JSON
