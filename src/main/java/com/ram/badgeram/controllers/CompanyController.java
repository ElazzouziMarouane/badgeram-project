package com.ram.badgeram.controllers;
import com.ram.badgeram.models.dto.CompanyDTO;
import com.ram.badgeram.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
class CompanyController {
    private final CompanyService service;


    @GetMapping
    public Page<CompanyDTO> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/deleted")
    public Page<CompanyDTO> getDeleted(Pageable pageable) {
        return service.findDeleted(pageable);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        service.restore(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public Page<CompanyDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return service.search(keyword, pageable);
    }

    @PostMapping
    public CompanyDTO create(@RequestBody CompanyDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CompanyDTO update(@PathVariable Long id, @RequestBody CompanyDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
