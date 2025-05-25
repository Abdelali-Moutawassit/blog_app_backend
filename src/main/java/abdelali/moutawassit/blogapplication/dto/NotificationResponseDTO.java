package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponseDTO {
    private Long id;
    private String content;
    private String type;
    private boolean isRead;
    private LocalDateTime createdAt;

    private Long receiverId;
    private String receiverUsername;

    private Long senderId;
    private String senderUsername;
}
