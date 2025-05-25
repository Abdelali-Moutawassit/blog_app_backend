package abdelali.moutawassit.blogapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String content;

    private LocalDateTime sentAt;

    private boolean isRead;

    // === Relations ===

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;  // Utilisateur qui envoie le message

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;  // Utilisateur qui reçoit le message
}
