package com.weatherBackend.EXTERNAL_API.service.implementation;

import com.weatherBackend.EXTERNAL_API.DTOs.*;
import com.weatherBackend.EXTERNAL_API.client.WeatherApiClient;
import com.weatherBackend.EXTERNAL_API.exception.WeatherServiceException;
import com.weatherBackend.EXTERNAL_API.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherApiClient weatherApiClient;

    @Override
    public List<CityResponseDTO> searchCities(String query) {

        return weatherApiClient.searchCities(query)
                .stream()
                .map(city -> {
                    CityResponseDTO cityResponseDTO = new CityResponseDTO();

                    cityResponseDTO.setId(city.getId());
                    cityResponseDTO.setName(city.getName());
                    cityResponseDTO.setRegion(city.getRegion());
                    cityResponseDTO.setCountry(city.getCountry());
                    cityResponseDTO.setLatitude(city.getLat());
                    cityResponseDTO.setLongitude(city.getLon());

                    return cityResponseDTO;
                })
                .toList();
    }

    @Override
    public CurrentWeatherResponseDTO getCurrentWeather(Long cityId) {

        //Recup les données météos auprès du fournisseur
        WeatherApiCurrentDTO weatherData;

        try {
            weatherData = weatherApiClient.getCurrentWeather(cityId);
        } catch (Exception e) {
            throw new WeatherServiceException("Unable to retrieve current weather", e);
        }

        //Build notre DTO en recup les infos dans les objets imbriqués.
        CurrentWeatherResponseDTO responseDTO = new CurrentWeatherResponseDTO();

        responseDTO.setCityName(weatherData.getLocationDTO().getName());
        responseDTO.setCountry(weatherData.getLocationDTO().getCountry());

        responseDTO.setTemperatureC(weatherData.getCurrentDataDTO().getTempC());
        responseDTO.setFeelslikeC(weatherData.getCurrentDataDTO().getFeelslikeC());
        responseDTO.setHumidity(weatherData.getCurrentDataDTO().getHumidity());
        responseDTO.setWindKph(weatherData.getCurrentDataDTO().getWindKph());
        responseDTO.setWindDirection(weatherData.getCurrentDataDTO().getWindDir());
        responseDTO.setConditionText(weatherData.getCurrentDataDTO().getConditionDTO().getText());
        responseDTO.setConditionIcon(weatherData.getCurrentDataDTO().getConditionDTO().getIcon());

        return responseDTO;

    }

    @Override
    public List<ForecastResponseDTO> getForecast(Long cityId, int days) {

        WeatherApiForecastDTO forecastDTO;

        try {
            forecastDTO = weatherApiClient.getForecast(cityId, days);
        } catch (Exception e) {
            throw new WeatherServiceException("Unable to retrieve weather forecast", e);
        }

        return forecastDTO.getForecast().getForecastday()
                .stream()
                .map(forecastDay -> {

                    ForecastResponseDTO responseDTO = new ForecastResponseDTO();

                    responseDTO.setDate(forecastDay.getDate());
                    responseDTO.setMaxTempC(forecastDay.getDay().getMaxTempC());
                    responseDTO.setMinTempC(forecastDay.getDay().getMinTempC());
                    responseDTO.setAvgTempC(forecastDay.getDay().getAvgTempC());
                    responseDTO.setMaxWindKph(forecastDay.getDay().getMaxWindKph());
                    responseDTO.setAvgHumidity(forecastDay.getDay().getAvgHumidity());
                    responseDTO.setDailyChangeOfRain(forecastDay.getDay().getDailyChanceOfRain());
                    responseDTO.setConditionText(forecastDay.getDay().getCondition().getText());
                    responseDTO.setConditionIcon(forecastDay.getDay().getCondition().getIcon());

                    return responseDTO;
                })
                .toList();

    }
}
