package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReactionResponseDTO {
    private Long id;
    private Long userId;
    private String username;
    private Long postId;
    private String type;
    private LocalDateTime reactedAt;
}
