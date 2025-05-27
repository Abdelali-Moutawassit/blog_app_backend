package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.PostShareRequestDTO;
import abdelali.moutawassit.blogapplication.dto.PostShareResponseDTO;
import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.PostShare;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;

public class PostShareMapper {

    public static PostShareResponseDTO toResponseDTO(PostShare share) {
        if (share == null) return null;

        return PostShareResponseDTO.builder()
                .id(share.getId())
                .userId(share.getUser().getId())
                .username(share.getUser().getUsername())
                .postId(share.getPost().getId())
                .reactedAt(share.getSharedAt())
                .build();
    }

    public static PostShare toEntity(PostShareRequestDTO dto, User user, Post post) {
        if (dto == null || user == null || post == null) return null;

        return PostShare.builder()
                .user(user)
                .post(post)
                .sharedAt(LocalDateTime.now())
                .build();
    }
}
