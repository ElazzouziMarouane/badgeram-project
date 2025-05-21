package com.ram.badgeram.controllers;


import com.ram.badgeram.models.dto.EmployeeDTO;
import com.ram.badgeram.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public Page<EmployeeDTO> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/deleted")
    public Page<EmployeeDTO> getDeleted(Pageable pageable) {
        return service.findDeleted(pageable);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        service.restore(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public Page<EmployeeDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return service.searchByName(keyword, pageable);
    }

    @PostMapping
    public EmployeeDTO create(@RequestBody EmployeeDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
