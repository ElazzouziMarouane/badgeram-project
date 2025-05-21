package com.ram.badgeram.controllers;

import com.ram.badgeram.models.dto.BadgeDTO;
import com.ram.badgeram.services.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/badges")
class BadgeController {
    private final BadgeService service;

    @GetMapping

    public Page<BadgeDTO> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/deleted")
    public Page<BadgeDTO> getDeleted(Pageable pageable) {
        return service.findDeleted(pageable);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        service.restore(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public Page<BadgeDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return service.searchByEmployee(keyword, pageable);
    }

    @PostMapping
    public BadgeDTO create(@RequestBody BadgeDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public BadgeDTO update(@PathVariable Long id, @RequestBody BadgeDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
