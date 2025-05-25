package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.NotificationRequestDTO;
import abdelali.moutawassit.blogapplication.dto.NotificationResponseDTO;
import abdelali.moutawassit.blogapplication.mapper.NotificationMapper;
import abdelali.moutawassit.blogapplication.model.Notification;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.NotificationRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    // Ajouter une notification
    public NotificationResponseDTO createNotification(NotificationRequestDTO dto) {
        Optional<User> receiverOpt = userRepository.findById(dto.getReceiverId());
        if (receiverOpt.isEmpty()) {
            throw new IllegalArgumentException("Receiver not found");
        }

        User receiver = receiverOpt.get();
        User sender = null;

        if (dto.getSenderId() != null) {
            sender = userRepository.findById(dto.getSenderId())
                    .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        }

        Notification notification = NotificationMapper.toEntity(dto, receiver, sender);
        Notification saved = notificationRepository.save(notification);
        return NotificationMapper.toResponseDTO(saved);
    }

    // Récupérer toutes les notifications pour un utilisateur
    public List<NotificationResponseDTO> getUserNotifications(Long receiverId) {
        return notificationRepository.findByReceiverIdOrderByCreatedAtDesc(receiverId).stream()
                .map(NotificationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Marquer une notification comme lue
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        notification.setRead(true);
        notificationRepository.save(notification);
    }

    // Supprimer une notification
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new IllegalArgumentException("Notification does not exist");
        }
        notificationRepository.deleteById(id);
    }
}
