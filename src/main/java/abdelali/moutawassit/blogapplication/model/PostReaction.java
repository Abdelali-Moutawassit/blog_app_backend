package abdelali.moutawassit.blogapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_reactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum ReactionType {
        LIKE,
        LOVE,
        HAHA,
        SAD,
        ANGRY
        // tu peux ajouter d'autres types si tu veux
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType type;

    private LocalDateTime reactedAt;

    // === Relations ===

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Qui a réagi

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Sur quel post
}
