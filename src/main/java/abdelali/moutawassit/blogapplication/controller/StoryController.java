package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.StoryRequestDTO;
import abdelali.moutawassit.blogapplication.dto.StoryResponseDTO;
import abdelali.moutawassit.blogapplication.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    /**
     * Créer une nouvelle story.
     */
    @PostMapping("/create/{userId}")
    public ResponseEntity<StoryResponseDTO> createStory(
            @PathVariable Long userId,
            @RequestBody StoryRequestDTO dto) {

        StoryResponseDTO response = storyService.createStory(dto, userId);
        return ResponseEntity.ok(response);
    }

    /**
     * Récupérer toutes les stories actives (non expirées).
     */
    @GetMapping("/active")
    public ResponseEntity<List<StoryResponseDTO>> getActiveStories() {
        return ResponseEntity.ok(storyService.getActiveStories());
    }

    /**
     * Récupérer les stories d’un utilisateur spécifique (non expirées).
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StoryResponseDTO>> getUserStories(@PathVariable Long userId) {
        return ResponseEntity.ok(storyService.getUserStories(userId));
    }

    /**
     * Supprimer les stories expirées (optionnel, utile pour un cron job ou admin).
     */
    @DeleteMapping("/expired")
    public ResponseEntity<Void> deleteExpiredStories() {
        storyService.deleteExpiredStories();
        return ResponseEntity.noContent().build();
    }
}
