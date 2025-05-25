package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.CommentReactionRequestDTO;
import abdelali.moutawassit.blogapplication.dto.CommentReactionResponseDTO;
import abdelali.moutawassit.blogapplication.service.CommentReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment-reactions")
@RequiredArgsConstructor
public class CommentReactionController {

    private final CommentReactionService reactionService;

    // Ajouter une réaction à un commentaire
    @PostMapping("/react")
    public ResponseEntity<CommentReactionResponseDTO> reactToComment(
            @RequestParam Long userId,
            @RequestParam Long commentId,
            @RequestBody CommentReactionRequestDTO requestDTO) {

        CommentReactionResponseDTO response = reactionService.reactToComment(userId, commentId, requestDTO);
        return ResponseEntity.ok(response);
    }

    // Supprimer une réaction
    @DeleteMapping("/{reactionId}")
    public ResponseEntity<Void> removeReaction(@PathVariable Long reactionId) {
        reactionService.removeReaction(reactionId);
        return ResponseEntity.noContent().build();
    }

    // Obtenir toutes les réactions d’un commentaire
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<List<CommentReactionResponseDTO>> getReactionsForComment(@PathVariable Long commentId) {
        List<CommentReactionResponseDTO> responses = reactionService.getReactionsForComment(commentId);
        return ResponseEntity.ok(responses);
    }

    // Obtenir toutes les réactions d’un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentReactionResponseDTO>> getReactionsByUser(@PathVariable Long userId) {
        List<CommentReactionResponseDTO> responses = reactionService.getReactionsByUser(userId);
        return ResponseEntity.ok(responses);
    }
}
