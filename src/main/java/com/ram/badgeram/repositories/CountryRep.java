package com.ram.badgeram.repositories;

import com.ram.badgeram.models.entity.Airport;
import com.ram.badgeram.models.entity.Badge;
import com.ram.badgeram.models.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRep extends JpaRepository<Country,Long> {
    Page<Country> findByDeletedFalse(Pageable pageable);
    Page<Country> findByDeletedTrue(Pageable pageable);
    Page<Country> findByNameContainingIgnoreCaseAndDeletedFalse(String name, Pageable pageable);
}

