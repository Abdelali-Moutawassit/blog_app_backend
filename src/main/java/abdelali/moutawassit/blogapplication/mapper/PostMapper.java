package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.PostRequestDTO;
import abdelali.moutawassit.blogapplication.dto.PostResponseDTO;
import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.User;

public class PostMapper {

    public static PostResponseDTO toResponseDTO(Post post) {
        if (post == null) return null;

        return PostResponseDTO.builder()
                .id(post.getId())
                .userId(post.getUser() != null ? post.getUser().getId() : null)
                .userName(post.getUser() != null ? post.getUser().getUsername() : null)
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static Post toEntity(PostRequestDTO dto, User user) {
        if (dto == null) return null;

        return Post.builder()
                .user(user)  // L'entité User doit être fournie ici
                .content(dto.getContent())
                .imageUrl(dto.getImageUrl())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : java.time.LocalDateTime.now())
                .build();
    }
}
