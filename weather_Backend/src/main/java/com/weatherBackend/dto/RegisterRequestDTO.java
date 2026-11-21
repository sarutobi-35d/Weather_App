package com.weatherBackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    @NotBlank(message = "Nom obligatoire")
    @Size(min = 2, message = "Le nom de contenir 02 caractères minimums")
    private String name;

    @NotBlank(message = "Adresse email obligatoire")
    @Email
    private String email;

    @NotBlank(message = "Mot de passe obligatoire")
    private String password;
}
