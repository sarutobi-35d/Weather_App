package com.weatherBackend.exception;

public class FavoriteNotFoundException extends RuntimeException{
    public FavoriteNotFoundException(Long favoriteId){
        super("Favori introuvable ou inaccessible: " + favoriteId);
    }
}
