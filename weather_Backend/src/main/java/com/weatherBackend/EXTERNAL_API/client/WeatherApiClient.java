package com.weatherBackend.EXTERNAL_API.client;

import com.weatherBackend.EXTERNAL_API.DTOs.WeatherApiCityDTO;
import com.weatherBackend.EXTERNAL_API.DTOs.WeatherApiCurrentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class WeatherApiClient {

    private final RestClient weatherRestClient;
    private final String apiKey;

    public WeatherApiClient(RestClient weatherRestClient, @Value("${weather.api.key}") String apiKey) {
        this.weatherRestClient = weatherRestClient;
        this.apiKey = apiKey;
    }

    public List<WeatherApiCityDTO> searchCities(String query){

        return weatherRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search.json") //Indique au client HTTP quelle ressource il doit demander au serveur distant(API externe)
                        .queryParam("key", apiKey)
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<List<WeatherApiCityDTO>>() {});
    }

    //Methode pour consulter la météo d'une ville
    public WeatherApiCurrentDTO getCurrentWeather(Long cityId){

        return weatherRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", "id:" + cityId)
                        .build())
                .retrieve() //Déclenche la recup de la réponse HTTP
                .body(WeatherApiCurrentDTO.class); // Ask à Spring de convertir le JSON recu en objet JAVA
    }

}
