package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDTO {
    private Long userId;     // Utilisateur qui commente
    private Long postId;     // Post commenté
    private String content;  // Texte du commentaire
    private LocalDateTime createdAt;  // Optionnel, souvent géré côté backend
    private String profileImageUrl;
}
