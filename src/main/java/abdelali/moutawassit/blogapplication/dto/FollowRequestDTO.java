package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequestDTO {
    private Long followerId;
    private Long followeeId;
}
