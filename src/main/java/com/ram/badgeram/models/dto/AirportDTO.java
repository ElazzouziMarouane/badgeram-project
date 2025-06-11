package com.ram.badgeram.models.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportDTO {
    private Long id;
    private String name;
    private String airportCode;
    private CityTempDTO city;
}