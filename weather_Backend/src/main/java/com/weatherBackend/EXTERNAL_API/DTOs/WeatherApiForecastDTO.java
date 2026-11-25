package com.weatherBackend.EXTERNAL_API.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WeatherApiForecastDTO {

    private Forecast forecast;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Forecast {

        private List<ForecastDay> forecastday;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ForecastDay {

        private String date;
        private Day day;
        private Astro astro;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Day {

        @JsonProperty("maxtemp_c")
        private Double maxTempC;

        @JsonProperty("mintemp_c")
        private Double minTempC;

        @JsonProperty("avgtemp_c")
        private Double avgTempC;

        @JsonProperty("maxwind_kph")
        private Double maxWindKph;

        @JsonProperty("avghumidity")
        private Double avgHumidity;

        @JsonProperty("daily_chance_of_rain")
        private Integer dailyChanceOfRain;

        private WeatherApiConditionDTO condition;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Astro {

        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
        private String moon_phase;
        private Integer moon_illumination;
    }

}

//LES OBJETS IMBRIQUÉS SONT DES CLASSES STATIC(J'AI JUSTE VOULU TESTER UNE AUTRE METHODE)
