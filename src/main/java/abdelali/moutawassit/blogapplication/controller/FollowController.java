package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.FollowRequestDTO;
import abdelali.moutawassit.blogapplication.dto.FollowResponseDTO;
import abdelali.moutawassit.blogapplication.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    /**
     * Endpoint pour suivre un utilisateur
     * POST /api/follows
     */
    @PostMapping
    public ResponseEntity<FollowResponseDTO> followUser(@RequestBody FollowRequestDTO dto) {
        FollowResponseDTO response = followService.followUser(dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint pour ne plus suivre un utilisateur
     * DELETE /api/follows/unfollow?followerId=1&followeeId=2
     */
    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(
            @RequestParam Long followerId,
            @RequestParam Long followeeId
    ) {
        followService.unfollowUser(followerId, followeeId);
        return ResponseEntity.ok("Unfollow effectué avec succès.");
    }
}
