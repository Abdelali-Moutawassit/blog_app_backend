package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedPostRequestDTO {
    private Long userId;
    private Long postId;
}
