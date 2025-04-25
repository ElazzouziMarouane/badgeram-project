package com.ram.badgeram.models.entity;



import com.ram.badgeram.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter



public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airportCode;

    private String name;
    private boolean deleted;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;


}

