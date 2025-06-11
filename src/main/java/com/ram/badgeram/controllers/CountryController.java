package com.ram.badgeram.controllers;
import com.ram.badgeram.models.dto.CountryDTO;
import com.ram.badgeram.models.entity.Country;
import com.ram.badgeram.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
class CountryController {
    private final CountryService service;


    @GetMapping
    public Page<CountryDTO> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/deleted")
    public Page<CountryDTO> getDeleted(Pageable pageable) {
        return service.findDeleted(pageable);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        service.restore(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public Page<CountryDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return service.search(keyword, pageable);
    }

    @PostMapping
    public CountryDTO create(@RequestBody CountryDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CountryDTO update(@PathVariable Long id, @RequestBody CountryDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
