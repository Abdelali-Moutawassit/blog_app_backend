package abdelali.moutawassit.blogapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mediaUrl; // URL vers l’image ou vidéo

    private String caption;  // Texte ou description facultative

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    // === Relations ===

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Auteur de la story
}
