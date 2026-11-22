package com.weatherBackend.exception;

public class FavoriteLimitExceededException extends RuntimeException{
    public FavoriteLimitExceededException(){
        super("Vous ne pouvez pas ajouter plus de 10 villes favorites.");
    }
}
