package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentReactionRequestDTO {
    private Long userId;
    private Long commentId;
    private String type; // "LIKE", "LOVE", "HAHA", etc.
}
