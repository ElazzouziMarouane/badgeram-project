package com.ram.badgeram.repositories;

import com.ram.badgeram.models.entity.Airport;
import com.ram.badgeram.models.entity.Badge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRep extends JpaRepository<Airport,Long> {
    Page<Airport> findByDeletedFalse(Pageable pageable);
    Page<Airport> findByDeletedTrue(Pageable pageable);

    @Query("SELECT a FROM Airport a WHERE a.deleted = false AND " +
            "(LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.city.name) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Airport> searchByNameOrCityName(
            @Param("search") String searchTerm,
            Pageable pageable);
}

