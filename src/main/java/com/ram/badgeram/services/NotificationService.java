package com.ram.badgeram.services;
import com.ram.badgeram.models.dto.NotificationDTO;
import com.ram.badgeram.models.entity.Notification;
import com.ram.badgeram.models.mapper.NotificationMapper;
import com.ram.badgeram.repositories.NotificationRep;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRep notificationRep;
    private final NotificationMapper notificationMapper;

    public Page<NotificationDTO> getAll(Pageable pageable) {
        return notificationRep.findByDeletedFalse(pageable).map(notificationMapper::toDTO);
    }
    public Page<NotificationDTO> findDeleted(Pageable pageable) {
        return notificationRep.findByDeletedTrue(pageable)
                .map(notificationMapper::toDTO);
    }
    public void restore(Long id) {
        Notification notification = notificationRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found by this ID : " + id));
        notification.setDeleted(false);
        notificationRep.save(notification);
    }
    public Page<NotificationDTO> searchByEmployeeName(String name, Pageable pageable) {
        if (name == null || name.trim().isEmpty()) {
            return notificationRep.findByDeletedFalse(pageable)
                    .map(notificationMapper::toDTO);
        } else {
            return notificationRep.searchByEmployeeName(name, pageable)
                    .map(notificationMapper::toDTO);
        }
    }

    public NotificationDTO create(NotificationDTO dto) {
        return notificationMapper.toDTO(notificationRep.save(notificationMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Notification notification = notificationRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found by this ID : " + id));
        notification.setDeleted(true);
        notificationRep.save(notification);
    }
}

