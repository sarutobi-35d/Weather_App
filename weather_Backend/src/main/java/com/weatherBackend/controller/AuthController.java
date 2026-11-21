package com.weatherBackend.controller;

import com.weatherBackend.dto.RegisterRequestDTO;
import com.weatherBackend.dto.UserResponseDTO;
import com.weatherBackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //endpoint de recup du jeton csrf... Car CSRF non disable() dans la configuration
    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(CsrfToken csrfToken){
        return csrfToken;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO requestDTO){

        UserResponseDTO responseDTO = authService.register(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
