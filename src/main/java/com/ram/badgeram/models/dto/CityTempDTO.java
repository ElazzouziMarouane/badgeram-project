package com.ram.badgeram.models.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityTempDTO {
    private Long id;
    private String name;
    private CountryTempDTO country;
}