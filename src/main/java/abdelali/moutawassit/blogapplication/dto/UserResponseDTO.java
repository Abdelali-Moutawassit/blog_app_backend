package abdelali.moutawassit.blogapplication.dto;

import abdelali.moutawassit.blogapplication.model.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String bio;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private List<PostResponseDTO> posts;
}
