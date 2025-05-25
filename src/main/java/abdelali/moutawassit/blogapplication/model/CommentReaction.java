package abdelali.moutawassit.blogapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment_reactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public enum ReactionType {
        LIKE,
        LOVE,
        HAHA,
        SAD,
        ANGRY
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
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment; // Sur quel commentaire
}
