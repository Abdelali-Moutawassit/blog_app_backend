package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.SavedPostRequestDTO;
import abdelali.moutawassit.blogapplication.dto.SavedPostResponseDTO;
import abdelali.moutawassit.blogapplication.service.SavedPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-posts")
@RequiredArgsConstructor
public class SavedPostController {

    private final SavedPostService savedPostService;

    /**
     * Sauvegarder un post pour un utilisateur
     */
    @PostMapping
    public ResponseEntity<SavedPostResponseDTO> savePost(@RequestBody SavedPostRequestDTO requestDTO) {
        SavedPostResponseDTO response = savedPostService.savePost(requestDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer un post sauvegardé
     */
    @DeleteMapping
    public ResponseEntity<Void> unsavePost(@RequestParam Long userId, @RequestParam Long postId) {
        savedPostService.unsavePost(userId, postId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer tous les posts sauvegardés d'un utilisateur
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SavedPostResponseDTO>> getSavedPostsByUser(@PathVariable Long userId) {
        List<SavedPostResponseDTO> savedPosts = savedPostService.getSavedPostsByUser(userId);
        return ResponseEntity.ok(savedPosts);
    }
}
