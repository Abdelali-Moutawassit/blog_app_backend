package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.SavedPostRequestDTO;
import abdelali.moutawassit.blogapplication.dto.SavedPostResponseDTO;
import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.SavedPost;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;

public class SavedPostMapper {

    public static SavedPost toEntity(SavedPostRequestDTO dto, User user, Post post) {
        if (dto == null || user == null || post == null) return null;

        return SavedPost.builder()
                .user(user)
                .post(post)
                .savedAt(LocalDateTime.now())
                .build();
    }

    public static SavedPostResponseDTO toResponseDTO(SavedPost savedPost) {
        if (savedPost == null) return null;

        return SavedPostResponseDTO.builder()
                .id(savedPost.getId())
                .userId(savedPost.getUser().getId())
                .username(savedPost.getUser().getUsername())
                .postId(savedPost.getPost().getId())
                .postContent(savedPost.getPost().getContent())
                .savedAt(savedPost.getSavedAt())
                .build();
    }
}
