package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRequestDTO {
    private Long senderId;
    private Long receiverId;
    private String content;
}
