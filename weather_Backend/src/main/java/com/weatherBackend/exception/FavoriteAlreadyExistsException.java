package com.weatherBackend.exception;

public class FavoriteAlreadyExistsException extends RuntimeException{
    public FavoriteAlreadyExistsException(String cityName){
        super("Cette ville est déjà dans vos favoris: " + cityName);
    }
}
