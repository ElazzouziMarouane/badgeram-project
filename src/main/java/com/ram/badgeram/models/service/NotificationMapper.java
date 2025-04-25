package com.ram.badgeram.models.service;

import com.ram.badgeram.models.dto.NotificationDTO;
import com.ram.badgeram.models.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface NotificationMapper {
    NotificationDTO toDTO(Notification notification);
    Notification toEntity(NotificationDTO notificationDTO);

    @Named("NotDeleted")
    default List<NotificationDTO> toDtoListNotDeleted(List<Notification> notifications) {
        if (notifications == null) return null;
        return notifications.stream()
                .filter(n -> !n.isDeleted())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}