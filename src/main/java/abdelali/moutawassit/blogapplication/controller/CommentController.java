package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.CommentRequestDTO;
import abdelali.moutawassit.blogapplication.dto.CommentResponseDTO;
import abdelali.moutawassit.blogapplication.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Créer un commentaire lié à un post et un utilisateur
    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentResponseDTO> createComment(
            @PathVariable Long postId,
            @PathVariable Long userId,
            @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO createdComment = commentService.createComment(commentRequestDTO, userId, postId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // Récupérer un commentaire par son id
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable Long id) {
        CommentResponseDTO comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    // Récupérer tous les commentaires d’un post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByPost(@PathVariable Long postId) {
        List<CommentResponseDTO> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    // Mettre à jour un commentaire
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDTO commentRequestDTO) {
        CommentResponseDTO updatedComment = commentService.updateComment(id, commentRequestDTO);
        return ResponseEntity.ok(updatedComment);
    }

    // Supprimer un commentaire
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
