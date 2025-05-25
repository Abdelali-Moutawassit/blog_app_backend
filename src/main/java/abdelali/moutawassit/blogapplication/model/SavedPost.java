package abdelali.moutawassit.blogapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "saved_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime savedAt;

    // === Relations ===

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // L'utilisateur qui a sauvegardé le post

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Le post sauvegardé
}
