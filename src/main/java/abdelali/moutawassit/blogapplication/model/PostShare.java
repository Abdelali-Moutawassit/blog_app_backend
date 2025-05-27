package abdelali.moutawassit.blogapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "post_reactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime sharedAt;

    // === Relations ===

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Qui a réagi

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Sur quel post
}
