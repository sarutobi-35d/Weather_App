package com.weatherBackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "Adresse email obligatoire")
    @Email
    private String email;

    @NotBlank(message = "Mot de passe obligatoire")
    private String password;
}
