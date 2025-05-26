package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.SearchHistoryRequestDTO;
import abdelali.moutawassit.blogapplication.dto.SearchHistoryResponseDTO;
import abdelali.moutawassit.blogapplication.mapper.SearchHistoryMapper;
import abdelali.moutawassit.blogapplication.model.SearchHistory;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.SearchHistoryRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;
    private final UserRepository userRepository;

    // ✅ Enregistrer une recherche
    public SearchHistoryResponseDTO saveSearchHistory(Long userId, SearchHistoryRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID: " + userId));

        SearchHistory history = SearchHistoryMapper.toEntity(dto, user);
        SearchHistory saved = searchHistoryRepository.save(history);

        return SearchHistoryMapper.toResponseDTO(saved);
    }

    // 📜 Récupérer l’historique des recherches d’un utilisateur
    public List<SearchHistoryResponseDTO> getSearchHistoryByUser(Long userId) {
        return searchHistoryRepository.findByUserIdOrderBySearchedAtDesc(userId).stream()
                .map(SearchHistoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 🗑️ Supprimer une entrée spécifique
    public void deleteSearchHistoryById(Long id) {
        searchHistoryRepository.deleteById(id);
    }

    // 🧹 Supprimer tout l’historique d’un utilisateur
    public void clearUserSearchHistory(Long userId) {
        searchHistoryRepository.deleteByUserId(userId);
    }
}
