package com.weatherBackend.EXTERNAL_API.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ForecastResponseDTO {

    private String date;
    private Double maxTempC;
    private Double minTempC;
    private Double avgTempC;
    private Double maxWindKph;
    private Double avgHumidity;
    private Integer dailyChangeOfRain;
    private String conditionText;
    private String conditionIcon;

    //REPRESENTE LES DONNÉES DE NOTRE PROPRE API QUI SERA TRANSMIS AU FRONT
}
