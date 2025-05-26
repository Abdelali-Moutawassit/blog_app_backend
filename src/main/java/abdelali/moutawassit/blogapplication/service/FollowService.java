package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.FollowRequestDTO;
import abdelali.moutawassit.blogapplication.dto.FollowResponseDTO;
import abdelali.moutawassit.blogapplication.mapper.FollowMapper;
import abdelali.moutawassit.blogapplication.model.Follow;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.FollowRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowResponseDTO followUser(FollowRequestDTO dto) {
        Optional<User> followerOpt = userRepository.findById(dto.getFollowerId());
        Optional<User> followeeOpt = userRepository.findById(dto.getFolloweeId());

        if (followerOpt.isEmpty() || followeeOpt.isEmpty()) {
            throw new RuntimeException("Utilisateur introuvable.");
        }

        User follower = followerOpt.get();
        User followee = followeeOpt.get();

        // Vérifie si le follow existe déjà
        if (followRepository.findByFollowerAndFollowee(follower, followee).isPresent()) {
            throw new RuntimeException("Déjà suivi.");
        }

        Follow follow = FollowMapper.toEntity(dto, follower, followee);
        Follow saved = followRepository.save(follow);
        return FollowMapper.toResponseDTO(saved);
    }

    public void unfollowUser(Long followerId, Long followeeId) {
        Optional<User> followerOpt = userRepository.findById(followerId);
        Optional<User> followeeOpt = userRepository.findById(followeeId);

        if (followerOpt.isEmpty() || followeeOpt.isEmpty()) {
            throw new RuntimeException("Utilisateur introuvable.");
        }

        User follower = followerOpt.get();
        User followee = followeeOpt.get();

        Optional<Follow> followOpt = followRepository.findByFollowerAndFollowee(follower, followee);

        if (followOpt.isEmpty()) {
            throw new RuntimeException("Le follow n'existe pas.");
        }

        followRepository.delete(followOpt.get());
    }
}
