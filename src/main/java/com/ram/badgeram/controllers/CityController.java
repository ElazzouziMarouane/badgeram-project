package com.ram.badgeram.controllers;
import com.ram.badgeram.models.dto.CityDTO;
import com.ram.badgeram.models.entity.Country;
import com.ram.badgeram.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
class CityController {
    private final CityService service;

    @GetMapping
    public Page<CityDTO> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/deleted")
    public Page<CityDTO> getDeleted(Pageable pageable) {
        return service.findDeleted(pageable);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        service.restore(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public Page<CityDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return service.search(keyword, pageable);
    }

    @PostMapping
    public CityDTO create(@RequestBody CityDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CityDTO update(@PathVariable Long id, @RequestBody CityDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
