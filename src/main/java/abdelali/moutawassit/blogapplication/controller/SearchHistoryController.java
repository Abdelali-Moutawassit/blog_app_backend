package abdelali.moutawassit.blogapplication.controller;

import abdelali.moutawassit.blogapplication.dto.SearchHistoryRequestDTO;
import abdelali.moutawassit.blogapplication.dto.SearchHistoryResponseDTO;
import abdelali.moutawassit.blogapplication.service.SearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search-history")
@RequiredArgsConstructor
public class SearchHistoryController {

    private final SearchHistoryService searchHistoryService;

    // Ajouter une recherche à l'historique
    @PostMapping("/add/{userId}")
    public ResponseEntity<SearchHistoryResponseDTO> addSearchHistory(
            @PathVariable Long userId,
            @RequestBody SearchHistoryRequestDTO dto) {

        SearchHistoryResponseDTO response = searchHistoryService.saveSearchHistory(userId, dto);
        return ResponseEntity.ok(response);
    }

    // Récupérer l'historique de recherche d'un utilisateur
    @GetMapping("/{userId}")
    public ResponseEntity<List<SearchHistoryResponseDTO>> getUserSearchHistory(@PathVariable Long userId) {
        List<SearchHistoryResponseDTO> history = searchHistoryService.getSearchHistoryByUser(userId);
        return ResponseEntity.ok(history);
    }

    // Supprimer une entrée spécifique par son ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSearchHistoryById(@PathVariable Long id) {
        searchHistoryService.deleteSearchHistoryById(id);
        return ResponseEntity.noContent().build();
    }

    // Supprimer tout l'historique d'un utilisateur
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearUserSearchHistory(@PathVariable Long userId) {
        searchHistoryService.clearUserSearchHistory(userId);
        return ResponseEntity.noContent().build();
    }
}
