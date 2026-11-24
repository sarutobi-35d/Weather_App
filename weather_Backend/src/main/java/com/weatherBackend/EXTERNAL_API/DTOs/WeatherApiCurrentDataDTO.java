package com.weatherBackend.EXTERNAL_API.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherApiCurrentDataDTO {

    @JsonProperty("temp_c")
    private Double tempC;

    @JsonProperty("feelslike_c")
    private Double feelslikeC;

    private Integer humidity;

    @JsonProperty("wind_kph")
    private Double windKph;

    @JsonProperty("wind_dir")
    private String windDir;

    @JsonProperty("condition")
    private WeatherApiConditionDTO conditionDTO; // Car l'objet condition est imbriqué dans l'objet current dans le JSON.

}

//REPRESENTE LES DONNÉES MÉTÉOROLOGIQUES ACTUELLES
/*
    @JsonProperty permet à Jackson de faire la correspondance entre temp_c & tempC (qui est la convention d'ecriture des ptés en JAVA)...
    C'est un peu une facon de renommer.
 */