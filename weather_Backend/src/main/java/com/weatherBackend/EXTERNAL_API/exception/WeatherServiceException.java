package com.weatherBackend.EXTERNAL_API.exception;

public class WeatherServiceException extends RuntimeException{

    //TRANSMETTRE UN MESSAGE EXPLICATIF
    public WeatherServiceException(String message){
        super(message);
    }

    //TRANSMETTRE UN MESSAGE EXPLICATIF ET CONSERVER L'EXCEPTION
    // ORIGINALE COMME CAUSE(Permet de ne pas perdre l'origine technique d'une erreur)
    public WeatherServiceException(String message, Throwable cause){
        super(message, cause);
    }

}
