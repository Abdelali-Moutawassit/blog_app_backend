package abdelali.moutawassit.blogapplication.mapper;

import abdelali.moutawassit.blogapplication.dto.FollowRequestDTO;
import abdelali.moutawassit.blogapplication.dto.FollowResponseDTO;
import abdelali.moutawassit.blogapplication.model.Follow;
import abdelali.moutawassit.blogapplication.model.User;

import java.time.LocalDateTime;

public class FollowMapper {

    public static Follow toEntity(FollowRequestDTO dto, User follower, User followee) {
        if (dto == null || follower == null || followee == null) return null;

        return Follow.builder()
                .follower(follower)
                .followee(followee)
                .followedAt(LocalDateTime.now())
                .build();
    }

    public static FollowResponseDTO toResponseDTO(Follow follow) {
        if (follow == null) return null;

        return FollowResponseDTO.builder()
                .id(follow.getId())
                .followerId(follow.getFollower().getId())
                .followerUsername(follow.getFollower().getUsername())
                .followeeId(follow.getFollowee().getId())
                .followeeUsername(follow.getFollowee().getUsername())
                .followedAt(follow.getFollowedAt())
                .build();
    }
}
