package abdelali.moutawassit.blogapplication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String profileImageUrl;
}
