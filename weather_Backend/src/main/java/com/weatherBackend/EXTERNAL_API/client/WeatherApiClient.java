package com.weatherBackend.EXTERNAL_API.client;

import com.weatherBackend.EXTERNAL_API.DTOs.WeatherApiCityDTO;
import com.weatherBackend.EXTERNAL_API.DTOs.WeatherApiCurrentDTO;
import com.weatherBackend.EXTERNAL_API.DTOs.WeatherApiForecastDTO;
import com.weatherBackend.EXTERNAL_API.exception.WeatherApiClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

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

        try {

            return weatherRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search.json") //Indique au client HTTP quelle ressource il doit demander au serveur distant(API externe)
                            .queryParam("key", apiKey)
                            .queryParam("q", query)
                            .build())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<WeatherApiCityDTO>>() {});

        } catch (RestClientException exception) {
            throw new WeatherApiClientException("Impossoble d'obtenir le résultat de la recherche " +
                    " auprès de WeatherAPI", exception);
        }

    }

    //Methode pour consulter la météo d'une ville
    public WeatherApiCurrentDTO getCurrentWeather(Long cityId){

        try {

            return weatherRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/current.json")
                            .queryParam("key", apiKey)
                            .queryParam("q", "id:" + cityId)
                            .build())
                    .retrieve() //Déclenche la recup de la réponse HTTP et la traite
                    .body(WeatherApiCurrentDTO.class); // Ask à Spring de convertir le JSON recu en objet JAVA

        } catch (RestClientException exception){
            throw new WeatherApiClientException("Impossible de récupérer les conditions " +
                    " météorologiques auprès de WeatherAPI", exception);
        }

    }

    //METHODE POUR LA PREVISION
    public WeatherApiForecastDTO getForecast(Long cityId, int days){

        try {

            return weatherRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/forecast.json")
                            .queryParam("key", apiKey)
                            .queryParam("q", "id:" + cityId)
                            .queryParam("days", days)
                            .build())
                    .retrieve()
                    .body(WeatherApiForecastDTO.class);

        } catch (RestClientException exception){
            throw new WeatherApiClientException("Impossible de récupérer les prévisions " +
                    " météorologiques auprès de WeatherAPI ", exception);
        }

    }


}
