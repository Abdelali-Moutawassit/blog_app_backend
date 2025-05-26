package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.MessageRequestDTO;
import abdelali.moutawassit.blogapplication.dto.MessageResponseDTO;
import abdelali.moutawassit.blogapplication.mapper.MessageMapper;
import abdelali.moutawassit.blogapplication.model.Message;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.MessageRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    // Envoyer un message
    public MessageResponseDTO sendMessage(MessageRequestDTO dto) {
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Destinataire introuvable"));

        Message message = MessageMapper.toEntity(dto, sender, receiver);
        Message saved = messageRepository.save(message);
        return MessageMapper.toResponseDTO(saved);
    }

    // Récupérer tous les messages entre deux utilisateurs
    public List<MessageResponseDTO> getMessagesBetweenUsers(Long user1Id, Long user2Id) {
        List<Message> messages = messageRepository.findMessagesBetweenUsers(user1Id, user2Id);
        return messages.stream()
                .map(MessageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Marquer un message comme lu
    public void markAsRead(Long messageId) {
        Optional<Message> optional = messageRepository.findById(messageId);
        if (optional.isPresent()) {
            Message message = optional.get();
            message.setRead(true);
            messageRepository.save(message);
        }
    }
}
