package abdelali.moutawassit.blogapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostShareResponseDTO {
    private Long id;
    private Long userId;
    private String username;
    private Long postId;
    private LocalDateTime reactedAt;
}
