package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.MessageRequestDTO;
import abdelali.moutawassit.blogapplication.dto.MessageResponseDTO;
import abdelali.moutawassit.blogapplication.model.Message;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;

public class MessageMapper {

    public static Message toEntity(MessageRequestDTO dto, User sender, User receiver) {
        if (dto == null || sender == null || receiver == null) return null;

        return Message.builder()
                .sender(sender)
                .receiver(receiver)
                .content(dto.getContent())
                .sentAt(LocalDateTime.now())
                .isRead(false)
                .build();
    }

    public static MessageResponseDTO toResponseDTO(Message message) {
        if (message == null) return null;

        return MessageResponseDTO.builder()
                .id(message.getId())
                .senderId(message.getSender().getId())
                .senderUsername(message.getSender().getUsername())
                .receiverId(message.getReceiver().getId())
                .receiverUsername(message.getReceiver().getUsername())
                .content(message.getContent())
                .sentAt(message.getSentAt())
                .isRead(message.isRead())
                .build();
    }
}
