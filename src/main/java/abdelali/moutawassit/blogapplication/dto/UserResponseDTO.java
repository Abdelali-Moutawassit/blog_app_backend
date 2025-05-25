package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

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
}
