package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentReactionResponseDTO {
    private Long id;
    private Long userId;
    private String username;
    private Long commentId;
    private String type;
    private LocalDateTime reactedAt;
}
