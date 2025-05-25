package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {
    private Long id;
    private Long userId;
    private String userName;        // nom de l'auteur pour affichage (optionnel)
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
}
