package com.ram.badgeram.controllers;

import com.ram.badgeram.models.dto.NotificationDTO;
import com.ram.badgeram.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public Page<NotificationDTO> getAll(Pageable pageable) {
        return notificationService.getAll(pageable);
    }

    @GetMapping("/search")
    public Page<NotificationDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return notificationService.searchByEmployeeName(keyword, pageable);
    }

    @PostMapping
    public NotificationDTO create(@RequestBody NotificationDTO dto) {
        return notificationService.create(dto);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notificationService.delete(id);
    }

    @GetMapping("/deleted")
    public Page<NotificationDTO> getDeleted(Pageable pageable) {
        return notificationService.findDeleted(pageable);
    }

    @PutMapping("/restore/{id}")
    public void restore(@PathVariable Long id) {
        notificationService.restore(id);
    }
}
