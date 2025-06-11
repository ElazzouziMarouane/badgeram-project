package com.ram.badgeram.models.mapper;

import com.ram.badgeram.models.dto.NotificationDTO;
import com.ram.badgeram.models.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface NotificationMapper {
    NotificationDTO toDTO(Notification notification);
    @Mapping(target = "deleted", ignore = true )
    Notification toEntity(NotificationDTO notificationDTO);

    @Named("NotDeletedNotifications")
    default List<NotificationDTO> toDtoListNotDeleted(List<Notification> notifications) {
        if (notifications == null) return null;
        return notifications.stream()
                .filter(n -> !n.isDeleted())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}