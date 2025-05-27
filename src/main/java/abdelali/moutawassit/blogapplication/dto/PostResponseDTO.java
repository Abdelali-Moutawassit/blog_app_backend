package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;

    private UserRequestDTO user;
    private Set<UserRequestDTO> likedBy;
    private Set<UserRequestDTO> sharedBy;
    private List<CommentRequestDTO> comments;

    private int likeCount;
    private int shareCount;
}
