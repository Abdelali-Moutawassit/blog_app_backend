package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReactionRequestDTO {
    private Long userId;
    private Long postId;
    private String type; // Ex: "LIKE", "LOVE", etc.
}
