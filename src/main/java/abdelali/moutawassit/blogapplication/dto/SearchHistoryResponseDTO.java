package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchHistoryResponseDTO {
    private Long id;
    private String query;
    private LocalDateTime searchedAt;
    private Long userId;
    private String username;
}
