package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.CommentRequestDTO;
import abdelali.moutawassit.blogapplication.dto.CommentResponseDTO;
import abdelali.moutawassit.blogapplication.dto.UserRequestDTO;
import abdelali.moutawassit.blogapplication.model.Comment;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.model.Post;

public class CommentMapper {

    public static UserRequestDTO toUserDTO(User user) {
        return UserRequestDTO.builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }

    public static CommentResponseDTO toResponseDTO(Comment comment) {
        if (comment == null) return null;

        return CommentResponseDTO.builder()
                .id(comment.getId())
                .userId(comment.getUser() != null ? comment.getUser().getId() : null)
                .userName(comment.getUser() != null ? comment.getUser().getUsername() : null)
                .user(toUserDTO(comment.getUser()))
                .postId(comment.getPost() != null ? comment.getPost().getId() : null)
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .likeCount(comment.getLikeCount())
                .user(toUserDTO(comment.getUser()))
                .build();
    }

    public static Comment toEntity(CommentRequestDTO dto, User user, Post post) {
        if (dto == null) return null;

        return Comment.builder()
                .user(user)
                .post(post)
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : java.time.LocalDateTime.now())
                .likeCount(0)
                .build();
    }
}
