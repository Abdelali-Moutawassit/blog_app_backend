package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchHistoryRequestDTO {
    private String query;
    private Long userId;
}
