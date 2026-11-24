package com.weatherBackend.EXTERNAL_API.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherApiConditionDTO {

    private String text;
    private String icon;
}

//DTO separé car il s'agit d'un element imbriqué de la reponse JSON de WeatherAPI...
//Ce sont les infos contenues dans l'objet condition
