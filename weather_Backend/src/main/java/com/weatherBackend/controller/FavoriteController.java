package com.weatherBackend.controller;


import com.weatherBackend.config.CustomUserDetails;
import com.weatherBackend.dto.FavoriteCityRequestDTO;
import com.weatherBackend.dto.FavoriteCityResponseDTO;
import com.weatherBackend.service.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    /* @AuthenticationPrincipal : permet de recuperer le principal
        actuellement authentifié par Spring Security qui est notre CustomUserDetails */
    @PostMapping
    public ResponseEntity<FavoriteCityResponseDTO> addFavoriteCity(@Valid @RequestBody FavoriteCityRequestDTO cityRequestDTO,
                                                                   @AuthenticationPrincipal CustomUserDetails userDetails){

        FavoriteCityResponseDTO responseDTO = favoriteService.addFavorite(userDetails.getUserId(), cityRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

//    @GetMapping("/test")
//    public ResponseEntity<String> test(){
//        return ResponseEntity.ok("Succès du user");
//    }
}
