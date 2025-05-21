package com.ram.badgeram.models.entity;


import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter



public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean deleted;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;
}

