package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.CommentReactionRequestDTO;
import abdelali.moutawassit.blogapplication.dto.CommentReactionResponseDTO;
import abdelali.moutawassit.blogapplication.model.Comment;
import abdelali.moutawassit.blogapplication.model.CommentReaction;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;

public class CommentReactionMapper {

    public static CommentReactionResponseDTO toResponseDTO(CommentReaction reaction) {
        if (reaction == null) return null;

        return CommentReactionResponseDTO.builder()
                .id(reaction.getId())
                .userId(reaction.getUser().getId())
                .username(reaction.getUser().getUsername())
                .commentId(reaction.getComment().getId())
                .type(reaction.getType().name())
                .reactedAt(reaction.getReactedAt())
                .build();
    }

    public static CommentReaction toEntity(CommentReactionRequestDTO dto, User user, Comment comment) {
        if (dto == null || user == null || comment == null) return null;

        return CommentReaction.builder()
                .user(user)
                .comment(comment)
                .type(CommentReaction.ReactionType.valueOf(dto.getType().toUpperCase()))
                .reactedAt(LocalDateTime.now())
                .build();
    }
}