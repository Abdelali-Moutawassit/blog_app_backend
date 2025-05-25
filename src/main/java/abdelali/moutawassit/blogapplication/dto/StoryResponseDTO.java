package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryResponseDTO {
    private Long id;
    private Long userId;
    private String username;
    private String mediaUrl;
    private String caption;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
