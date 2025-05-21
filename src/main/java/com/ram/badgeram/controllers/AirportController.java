package com.ram.badgeram.controllers;
import com.ram.badgeram.models.dto.AirportDTO;
import com.ram.badgeram.services.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/airports")
class AirportController {
    private final AirportService service;

    @GetMapping
    public Page<AirportDTO> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/deleted")
    public Page<AirportDTO> getDeleted(Pageable pageable) {
        return service.findDeleted(pageable);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        service.restore(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public Page<AirportDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return service.search(keyword, pageable);
    }

    @PostMapping
    public AirportDTO create(@RequestBody AirportDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public AirportDTO update(@PathVariable Long id, @RequestBody AirportDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}