package abdelali.moutawassit.blogapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // Message de notification (ex : "Ahmed a aimé votre post")

    private String type;    // Exemple : "LIKE", "COMMENT", "FOLLOW", "SHARE"

    private boolean isRead; // True si l'utilisateur a vu la notification

    private LocalDateTime createdAt;

    // === Relations ===

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver; // L'utilisateur qui reçoit la notification

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender; // L'utilisateur qui a déclenché l’action (facultatif si système)

    // Optionnel : tu peux aussi ajouter une relation vers un Post ou un Comment s’il y a lieu
}
