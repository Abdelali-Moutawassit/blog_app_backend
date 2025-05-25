package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.StoryRequestDTO;
import abdelali.moutawassit.blogapplication.dto.StoryResponseDTO;
import abdelali.moutawassit.blogapplication.mapper.StoryMapper;
import abdelali.moutawassit.blogapplication.model.Story;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.StoryRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final UserRepository userRepository;

    /**
     * Ajouter une nouvelle story pour un utilisateur.
     */
    public StoryResponseDTO createStory(StoryRequestDTO dto, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Utilisateur introuvable avec l'id: " + userId);
        }

        Story story = StoryMapper.toEntity(dto, optionalUser.get());
        Story saved = storyRepository.save(story);
        return StoryMapper.toResponseDTO(saved);
    }

    /**
     * Récupérer toutes les stories non expirées.
     */
    public List<StoryResponseDTO> getActiveStories() {
        List<Story> activeStories = storyRepository.findByExpiresAtAfter(LocalDateTime.now());
        return activeStories.stream()
                .map(StoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les stories d’un utilisateur spécifique.
     */
    public List<StoryResponseDTO> getUserStories(Long userId) {
        List<Story> userStories = storyRepository.findByUserIdAndExpiresAtAfter(userId, LocalDateTime.now());
        return userStories.stream()
                .map(StoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Supprimer toutes les stories expirées (optionnel).
     */
    public void deleteExpiredStories() {
        List<Story> expired = storyRepository.findByExpiresAtBefore(LocalDateTime.now());
        storyRepository.deleteAll(expired);
    }
}
