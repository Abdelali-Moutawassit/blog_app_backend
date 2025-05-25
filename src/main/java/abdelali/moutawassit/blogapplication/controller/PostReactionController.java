package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.PostReactionRequestDTO;
import abdelali.moutawassit.blogapplication.dto.PostReactionResponseDTO;
import abdelali.moutawassit.blogapplication.service.PostReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts/{postId}/reactions")
@RequiredArgsConstructor
public class PostReactionController {

    private final PostReactionService postReactionService;

    /**
     * Ajouter ou remplacer une réaction
     */
    @PostMapping
    public ResponseEntity<PostReactionResponseDTO> reactToPost(
            @PathVariable Long postId,
            @RequestParam Long userId, // ou via auth dans un vrai projet
            @RequestBody PostReactionRequestDTO reactionRequestDTO) {

        PostReactionResponseDTO response = postReactionService.addReaction(userId, postId, reactionRequestDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer une réaction d’un utilisateur
     */
    @DeleteMapping
    public ResponseEntity<Void> removeReaction(
            @PathVariable Long postId,
            @RequestParam Long userId) {

        postReactionService.deleteReaction(userId, postId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtenir toutes les réactions d’un post
     */
    @GetMapping
    public ResponseEntity<List<PostReactionResponseDTO>> getReactionsForPost(@PathVariable Long postId) {
        List<PostReactionResponseDTO> reactions = postReactionService.getReactionsForPost(postId);
        return ResponseEntity.ok(reactions);
    }

    /**
     * Compter les types de réactions pour un post
     */
    @GetMapping("/counts")
    public ResponseEntity<Map<String, Long>> countReactionsByType(@PathVariable Long postId) {
        Map<String, Long> counts = postReactionService.countReactionsByType(postId);
        return ResponseEntity.ok(counts);
    }
}
