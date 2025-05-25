package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.PostReactionRequestDTO;
import abdelali.moutawassit.blogapplication.dto.PostReactionResponseDTO;
import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.PostReaction;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;

public class PostReactionMapper {

    public static PostReactionResponseDTO toResponseDTO(PostReaction reaction) {
        if (reaction == null) return null;

        return PostReactionResponseDTO.builder()
                .id(reaction.getId())
                .userId(reaction.getUser().getId())
                .username(reaction.getUser().getUsername())
                .postId(reaction.getPost().getId())
                .type(reaction.getType().name())
                .reactedAt(reaction.getReactedAt())
                .build();
    }

    public static PostReaction toEntity(PostReactionRequestDTO dto, User user, Post post) {
        if (dto == null || user == null || post == null) return null;

        return PostReaction.builder()
                .user(user)
                .post(post)
                .type(PostReaction.ReactionType.valueOf(dto.getType().toUpperCase()))
                .reactedAt(LocalDateTime.now())
                .build();
    }
}
