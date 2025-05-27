package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDTO {
    private Long id;
    private Long userId;
    private String userName;    // nom de l'utilisateur pour affichage
    private Long postId;
    private String content;
    private LocalDateTime createdAt;
    private int likeCount;      // nombre de likes sur ce commentaire
    private  UserRequestDTO user;
}
