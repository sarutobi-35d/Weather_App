package com.weatherBackend;

import com.weatherBackend.EXTERNAL_API.DTOs.WeatherApiCityDTO;
import com.weatherBackend.EXTERNAL_API.DTOs.WeatherApiForecastDTO;
import com.weatherBackend.EXTERNAL_API.client.WeatherApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class WeatherApiClientTest {

        @Autowired
        private WeatherApiClient weatherApiClient;

        @Test
        void shouldSearchCities() {

            List<WeatherApiCityDTO> cities =
                    weatherApiClient.searchCities("Paris");

            assertNotNull(cities);
            assertFalse(cities.isEmpty());

            cities.forEach(city ->
                    System.out.println(city.getName() + " - " + city.getCountry())
            );
        }

    @Test
    void shouldGetForecastSuccessfully() {

        // Identifiant WeatherAPI de Paris
        Long cityId = 2801268L;

        // Nombre de jours souhaité
        int days = 3;

        WeatherApiForecastDTO forecast =
                weatherApiClient.getForecast(cityId, days);

        assertNotNull(forecast);
        assertNotNull(forecast.getForecast());
        assertNotNull(forecast.getForecast().getForecastday());

        assertFalse(forecast.getForecast().getForecastday().isEmpty());

        forecast.getForecast().getForecastday().forEach(day -> {
            System.out.println("Date : " + day.getDate());
            System.out.println("Température maximale : " + day.getDay().getMaxTempC());
            System.out.println("Température minimale : " + day.getDay().getMinTempC());
            System.out.println("----------------------------");
        });
    }

}
