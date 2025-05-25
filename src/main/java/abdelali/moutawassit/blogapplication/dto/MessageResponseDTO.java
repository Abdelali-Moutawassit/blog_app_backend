package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponseDTO {
    private Long id;

    private Long senderId;
    private String senderUsername;

    private Long receiverId;
    private String receiverUsername;

    private String content;
    private LocalDateTime sentAt;
    private boolean isRead;
}
