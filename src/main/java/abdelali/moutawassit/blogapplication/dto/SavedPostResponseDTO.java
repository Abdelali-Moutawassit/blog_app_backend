package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedPostResponseDTO {
    private Long id;

    private Long userId;
    private String username;

    private Long postId;
    private String postContent;

    private LocalDateTime savedAt;
}
