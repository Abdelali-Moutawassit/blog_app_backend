package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDTO {
    private Long userId;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
}
