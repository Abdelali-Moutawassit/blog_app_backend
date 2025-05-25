package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.NotificationRequestDTO;
import abdelali.moutawassit.blogapplication.dto.NotificationResponseDTO;
import abdelali.moutawassit.blogapplication.model.Notification;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;

public class NotificationMapper {

    public static Notification toEntity(NotificationRequestDTO dto, User receiver, User sender) {
        if (dto == null || receiver == null) return null;

        return Notification.builder()
                .content(dto.getContent())
                .type(dto.getType())
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .receiver(receiver)
                .sender(sender)
                .build();
    }

    public static NotificationResponseDTO toResponseDTO(Notification notification) {
        if (notification == null) return null;

        return NotificationResponseDTO.builder()
                .id(notification.getId())
                .content(notification.getContent())
                .type(notification.getType())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .receiverId(notification.getReceiver().getId())
                .receiverUsername(notification.getReceiver().getUsername())
                .senderId(notification.getSender() != null ? notification.getSender().getId() : null)
                .senderUsername(notification.getSender() != null ? notification.getSender().getUsername() : null)
                .build();
    }
}