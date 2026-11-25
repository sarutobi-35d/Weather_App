package com.weatherBackend.EXTERNAL_API.exception;

public class WeatherApiClientException extends RuntimeException{

    public WeatherApiClientException(String message, Throwable cause){
        super(message, cause);
    }
    //TRANSFORMER LES EXCEPTIONS TECHNIQUES DE SPRING EN UNE EXCEPTION PROPRE À LA COUCHE D'INTÉGRATION
}
