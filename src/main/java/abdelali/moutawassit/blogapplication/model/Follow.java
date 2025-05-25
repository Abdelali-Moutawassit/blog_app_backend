package abdelali.moutawassit.blogapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "follows", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"follower_id", "followee_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime followedAt;

    // === Relations ===

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;  // Utilisateur qui suit

    @ManyToOne
    @JoinColumn(name = "followee_id", nullable = false)
    private User followee;  // Utilisateur suivi
}