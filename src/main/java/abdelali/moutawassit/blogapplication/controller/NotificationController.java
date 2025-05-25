package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.NotificationRequestDTO;
import abdelali.moutawassit.blogapplication.dto.NotificationResponseDTO;
import abdelali.moutawassit.blogapplication.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // facultatif, selon ton besoin
public class NotificationController {

    private final NotificationService notificationService;

    // Créer une notification
    @PostMapping
    public ResponseEntity<NotificationResponseDTO> createNotification(@RequestBody NotificationRequestDTO dto) {
        NotificationResponseDTO response = notificationService.createNotification(dto);
        return ResponseEntity.ok(response);
    }

    // Récupérer les notifications d'un utilisateur
    @GetMapping("/user/{receiverId}")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsForUser(@PathVariable Long receiverId) {
        List<NotificationResponseDTO> notifications = notificationService.getUserNotifications(receiverId);
        return ResponseEntity.ok(notifications);
    }

    // Marquer une notification comme lue
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.noContent().build();
    }

    // Supprimer une notification
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }
}