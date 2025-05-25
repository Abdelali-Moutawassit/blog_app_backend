package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDTO {
    private Long userId;             // L'id de l'utilisateur qui crée le post
    private String content;          // Texte du post
    private String imageUrl;         // URL d'image ou vidéo associée (optionnel)
    private LocalDateTime createdAt; // Optionnel, peut être géré côté backend
}
