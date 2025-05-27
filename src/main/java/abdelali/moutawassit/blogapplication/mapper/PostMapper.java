package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.CommentRequestDTO;
import abdelali.moutawassit.blogapplication.dto.PostRequestDTO;
import abdelali.moutawassit.blogapplication.dto.PostResponseDTO;
import abdelali.moutawassit.blogapplication.dto.UserRequestDTO;
import abdelali.moutawassit.blogapplication.model.Comment;
import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PostMapper {

    public static UserRequestDTO toUserDTO(User user) {
        return UserRequestDTO.builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }

    public static CommentRequestDTO toCommentDTO(Comment comment) {
        return CommentRequestDTO.builder()
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .userId(comment.getUser().getId())
                .profileImageUrl(comment.getUser().getProfileImageUrl())
                .build();
    }

    public static PostResponseDTO toResponseDTO(Post post) {
        if (post == null) return null;

        return PostResponseDTO.builder()
                .id(post.getId())
                .userId(post.getUser() != null ? post.getUser().getId() : null)
                .userName(post.getUser() != null ? post.getUser().getUsername() : null)
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .createdAt(post.getCreatedAt())
                .likedBy(post.getLikes().stream()
                        .map(PostMapper::toUserDTO)
                        .collect(Collectors.toSet()))

                .sharedBy(post.getSharedByUsers().stream()
                        .map(PostMapper::toUserDTO)
                        .collect(Collectors.toSet()))

                .comments(post.getComments().stream()
                        .map(PostMapper::toCommentDTO)
                        .collect(Collectors.toList()))

                .likeCount(post.getLikeCount())
                .shareCount(post.getShareCount())
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
