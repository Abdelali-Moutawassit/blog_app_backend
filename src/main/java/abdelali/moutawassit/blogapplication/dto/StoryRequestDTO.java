package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryRequestDTO {
    private Long userId;
    private String mediaUrl;
    private String caption;
}
