package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowResponseDTO {
    private Long id;

    private Long followerId;
    private String followerUsername;

    private Long followeeId;
    private String followeeUsername;

    private LocalDateTime followedAt;
}
