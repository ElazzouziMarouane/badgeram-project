package com.ram.badgeram.repositories;

import com.ram.badgeram.models.entity.Airport;
import com.ram.badgeram.models.entity.Badge;
import com.ram.badgeram.models.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRep extends JpaRepository<City,Long> {
    Page<City> findByDeletedFalse(Pageable pageable);
    Page<City> findByDeletedTrue(Pageable pageable);
    Page<City> findByNameContainingIgnoreCaseAndDeletedFalse(String name, Pageable pageable);
}

