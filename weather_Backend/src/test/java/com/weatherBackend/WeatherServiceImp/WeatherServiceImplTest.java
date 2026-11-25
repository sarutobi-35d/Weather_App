package com.weatherBackend.WeatherServiceImp;

import com.weatherBackend.EXTERNAL_API.DTOs.*;
import com.weatherBackend.EXTERNAL_API.client.WeatherApiClient;
import com.weatherBackend.EXTERNAL_API.exception.WeatherApiClientException;
import com.weatherBackend.EXTERNAL_API.exception.WeatherServiceException;
import com.weatherBackend.EXTERNAL_API.service.implementation.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class) //ACTIVE MOCKITO DANS JUnit5
public class WeatherServiceImplTest {

    @Mock //CRÉE UN FAUX OBJET QUI SIMULE UNE DÉPENDANCE
    private WeatherApiClient weatherApiClient;

    @InjectMocks //CRÉE OBJET QUE ON SOUHAITE TESTER ET LUI INJECTE LES DÉPENDANCES SIMULÉES
    private WeatherServiceImpl weatherService;

    //CAS NOMINAL: PRÉVISIONS RÉCUPÉRÉES CORRECTEMENT
     @Test
    void shouldGetForecastSuccessfully(){

         //CREATE OBJET PRINCIPAL
         WeatherApiForecastDTO forecastDTO = new WeatherApiForecastDTO();

         //CREATE OBJET Forecast
         WeatherApiForecastDTO.Forecast forecast = new WeatherApiForecastDTO.Forecast();

         //CREATE UNE JOURNÉE DE PRÉVISIONS
         WeatherApiForecastDTO.ForecastDay forecastDay = new WeatherApiForecastDTO.ForecastDay();

         //CREATE LES DONNÉES MÉTÉOS QUOTIDIENNES
         WeatherApiForecastDTO.Day day = new WeatherApiForecastDTO.Day();

         day.setMaxTempC(28.5);
         day.setMinTempC(21.6);
         day.setAvgTempC(24.5);
         day.setMaxWindKph(15.0);
         day.setAvgHumidity(75.0);
         day.setDailyChanceOfRain(20);

         //CREATE LES CONDITIONS MÉTÉOS
         WeatherApiConditionDTO condition = new WeatherApiConditionDTO();

         condition.setText("Partly cloudy");
         condition.setIcon("//cdn.weatherapi.com/weather/64x64/day/116.png");

         day.setCondition(condition);

         //ASSOCIATION DES OBJETS
         forecastDay.setDate("2026-11-26");
         forecastDay.setDay(day);

         forecast.setForecastday(List.of(forecastDay));

         forecastDTO.setForecast(forecast);

         //SIMULATION DU COMPORTEMENT DE WeatherApiClient
         when(weatherApiClient.getForecast(2801268L, 3))
                 .thenReturn(forecastDTO);

         //EXECUTE SERVICE AVEC LES DONNÉES FICTIVES
         List<ForecastResponseDTO> result = weatherService.getForecast(2801268L, 3);

         //VÉRIFIER LE RESULTAT GRACE À Assert
         assertNotNull(result);
         assertEquals(1, result.size());

         assertEquals(28.5, result.get(0).getMaxTempC());
         assertEquals(21.6, result.get(0).getMinTempC());
         assertEquals(24.5, result.get(0).getAvgTempC());
         assertEquals(75.0, result.get(0).getAvgHumidity());
         assertEquals(20, result.get(0).getDailyChangeOfRain());
         assertEquals("Partly cloudy", result.get(0).getConditionText());

         //VERIFIER L'APPEL DU SERVICE AVEC LA METHODE ET LES BONS PARAMÈTRES
         verify(weatherApiClient).getForecast(2801268L, 3);
     }

     //CAS D'ERREUR
     @Test
    void shouldPropagateExceptionWhenWeatherApiFails(){

         when(weatherApiClient.getForecast(2801268L, 3))
                 .thenThrow(new WeatherApiClientException("Impossible de récupérer les prévisions " +
                         " météorologiques auprès de WeatherAPI ", new RuntimeException("Erreur de connexion")));

         WeatherServiceException exception = assertThrows(WeatherServiceException.class, () ->
                 weatherService.getForecast(2801268L, 3));

         assertEquals("Unable to retrieve weather forecast", exception.getMessage());
         assertNotNull(exception.getCause());

         verify(weatherApiClient).getForecast(2801268L, 3);
     }



     @Test
    void shouldGetCurrentWeatherSuccessfully(){

         //DONNÉES FICTIVES DE LOCALISATION
         WeatherApiLocationDTO location = new WeatherApiLocationDTO();
         location.setName("Paris");
         location.setCountry("France");

         //DONNÉES MÉTÉO ACTUELLES
         WeatherApiCurrentDataDTO current = new WeatherApiCurrentDataDTO();
         current.setTempC(18.5);
         current.setFeelslikeC(17.2);
         current.setHumidity(65);
         current.setWindKph(12.0);
         current.setWindDir("NW");

         //CREATION DE LA CONDITION MÉTÉO
         WeatherApiConditionDTO condition = new WeatherApiConditionDTO();
         condition.setText("Partly cloudy");
         condition.setIcon("//cdn.weatherapi.com/weather/64x64/day/116.png");

         current.setConditionDTO(condition);

         //ASSEMBLAGE DE LA REPONSE WeatherAPI
         WeatherApiCurrentDTO weatherData = new WeatherApiCurrentDTO();

         weatherData.setLocationDTO(location);
         weatherData.setCurrentDataDTO(current);

         //CONFIG DU COMPORTEMENT DE MOCKITO
         when(weatherApiClient.getCurrentWeather(2801268L))
                 .thenReturn(weatherData);

         //EXCUTION DE LA MÉTHODE À TESTER
         CurrentWeatherResponseDTO response = weatherService.getCurrentWeather(2801268L);

         //VÉRIFICATION DES RESULTATS
         assertNotNull(response);
         assertEquals("Paris", response.getCityName());
         assertEquals("France", response.getCountry());
         assertEquals(18.5, response.getTemperatureC());
         assertEquals(17.2, response.getFeelslikeC());
         assertEquals(65, response.getHumidity());
         assertEquals(12.0, response.getWindKph());
         assertEquals("NW", response.getWindDirection());
         assertEquals("Partly cloudy", response.getConditionText());
         assertEquals(
                 "//cdn.weatherapi.com/weather/64x64/day/116.png",
                 response.getConditionIcon()
         );

         // VÉRIFICATION DE L'APPEL DU CLIENT
         verify(weatherApiClient).getCurrentWeather(2801268L);
     }

    @Test
    void shouldThrowWeatherServiceExceptionWhenCurrentWeatherApiFails() {

        // SIMULER ERREUR DU CLIENT WeatherAPI
        when(weatherApiClient.getCurrentWeather(2801268L))
                .thenThrow(new WeatherApiClientException("Impossible de récupérer les conditions " +
                        " météorologiques auprès de WeatherAPI", new RuntimeException("WeatherAPI indisponible") ));

        // VÉRIFIER QUE LE SERVICE LÈVE WeatherServiceException
        WeatherServiceException exception = assertThrows(
                WeatherServiceException.class,
                () -> weatherService.getCurrentWeather(2801268L)
        );

        // VÉRIFIER LE MESSAGE DE L'EXCEPTION
        assertEquals(
                "Unable to retrieve current weather",
                exception.getMessage()
        );

        // VÉRIFIER QUE LA CAUSE ORIGINALE EST CONSERVÉE
        assertNotNull(exception.getCause());

        // VÉRIFIER QUE LE CLIENT A ÉTÉ BIEN APPELÉ
        verify(weatherApiClient).getCurrentWeather(2801268L);
    }


}
