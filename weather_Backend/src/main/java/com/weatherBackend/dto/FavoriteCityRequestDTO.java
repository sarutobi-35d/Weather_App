package com.weatherBackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteCityRequestDTO {

    @NotNull(message = "Id de la ville necessaire")
    private Long cityId;

    @NotBlank(message = "Nom de la ville necéssaire")
    private String cityName;

    @NotBlank(message = "Pays necessaire")
    private String country;
}
