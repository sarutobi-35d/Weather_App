package com.weatherBackend;

import com.weatherBackend.EXTERNAL_API.DTOs.WeatherApiCityDTO;
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

}
