package com.weatherBackend.EXTERNAL_API.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WeatherApiConfig {

    /* Cette classe evite de dvoir répéter à chaque
    fois l'adresse de WeatherAPI dans chaque appel */
    @Bean
    public RestClient weatherRestClient(@Value("${weather.api.base-url}") String baseUrl) {

        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

}
