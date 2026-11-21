package com.weatherBackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    @NotBlank(message = "Nom obligatoire")
    @Min(value = 2, message = "Le nom de contenir 02 caractères minimums")
    private String name;

    @NotBlank(message = "Adresse email obligatoire")
    @Email
    private String email;

    @NotBlank(message = "Mot de passe obligatoire")
    private String password;
}
