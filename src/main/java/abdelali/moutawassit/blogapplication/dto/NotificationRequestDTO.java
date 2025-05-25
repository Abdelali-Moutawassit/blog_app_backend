package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestDTO {
    private Long receiverId;
    private Long senderId; // facultatif (peut être null)
    private String content;
    private String type; // e.g. "LIKE", "COMMENT", "FOLLOW"
}
