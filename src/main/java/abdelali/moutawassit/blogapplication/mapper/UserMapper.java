package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.UserRequestDTO;
import abdelali.moutawassit.blogapplication.dto.UserResponseDTO;
import abdelali.moutawassit.blogapplication.model.User;

public class UserMapper {

    public static UserResponseDTO toResponseDTO(User user) {
        if (user == null) return null;

        return UserResponseDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .bio(user.getBio())
                .profileImageUrl(user.getProfileImageUrl())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .fullName(dto.getFullName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword()) // à hasher dans le service plus tard
                .bio(dto.getBio())
                .profileImageUrl(dto.getProfileImageUrl())
                .build();
    }
}
