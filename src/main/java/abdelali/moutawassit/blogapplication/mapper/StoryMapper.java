package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.StoryRequestDTO;
import abdelali.moutawassit.blogapplication.dto.StoryResponseDTO;
import abdelali.moutawassit.blogapplication.model.Story;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;

public class StoryMapper {

    public static StoryResponseDTO toResponseDTO(Story story) {
        if (story == null) return null;

        return StoryResponseDTO.builder()
                .id(story.getId())
                .userId(story.getUser().getId())
                .username(story.getUser().getUsername())
                .mediaUrl(story.getMediaUrl())
                .caption(story.getCaption())
                .createdAt(story.getCreatedAt())
                .expiresAt(story.getExpiresAt())
                .build();
    }

    public static Story toEntity(StoryRequestDTO dto, User user) {
        if (dto == null || user == null) return null;

        LocalDateTime now = LocalDateTime.now();

        return Story.builder()
                .user(user)
                .mediaUrl(dto.getMediaUrl())
                .caption(dto.getCaption())
                .createdAt(now)
                .expiresAt(now.plusHours(24)) // expire après 24h
                .build();
    }
}
