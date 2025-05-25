package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.SearchHistoryRequestDTO;
import abdelali.moutawassit.blogapplication.dto.SearchHistoryResponseDTO;
import abdelali.moutawassit.blogapplication.model.SearchHistory;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;

public class SearchHistoryMapper {

    public static SearchHistory toEntity(SearchHistoryRequestDTO dto, User user) {
        if (dto == null || user == null) return null;

        return SearchHistory.builder()
                .query(dto.getQuery())
                .searchedAt(LocalDateTime.now())
                .user(user)
                .build();
    }

    public static SearchHistoryResponseDTO toResponseDTO(SearchHistory entity) {
        if (entity == null) return null;

        return SearchHistoryResponseDTO.builder()
                .id(entity.getId())
                .query(entity.getQuery())
                .searchedAt(entity.getSearchedAt())
                .userId(entity.getUser().getId())
                .username(entity.getUser().getUsername())
                .build();
    }
}
