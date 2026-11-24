package com.weatherBackend.EXTERNAL_API.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityResponseDTO {

    private Long id;
    private String name;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;
}

//CET DTO PREPARE LES DONNÉES QUE LE BACK VA TRANSMETTRE AU FRONT(REACT)