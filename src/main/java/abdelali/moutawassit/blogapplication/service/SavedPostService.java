package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.SavedPostRequestDTO;
import abdelali.moutawassit.blogapplication.dto.SavedPostResponseDTO;
import abdelali.moutawassit.blogapplication.exception.ResourceNotFoundException;
import abdelali.moutawassit.blogapplication.mapper.SavedPostMapper;
import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.SavedPost;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.PostRepository;
import abdelali.moutawassit.blogapplication.repository.SavedPostRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavedPostService {

    private final SavedPostRepository savedPostRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public SavedPostResponseDTO savePost(SavedPostRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec ID : " + dto.getUserId()));
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post non trouvé avec ID : " + dto.getPostId()));

        // Vérifier si déjà sauvegardé
        boolean alreadySaved = savedPostRepository.existsByUserAndPost(user, post);
        if (alreadySaved) {
            throw new IllegalStateException("Ce post est déjà sauvegardé par cet utilisateur.");
        }

        SavedPost savedPost = SavedPostMapper.toEntity(dto, user, post);
        savedPost = savedPostRepository.save(savedPost);
        return SavedPostMapper.toResponseDTO(savedPost);
    }

    public void unsavePost(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec ID : " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post non trouvé avec ID : " + postId));

        SavedPost savedPost = savedPostRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new ResourceNotFoundException("Ce post n'est pas sauvegardé par cet utilisateur."));
        savedPostRepository.delete(savedPost);
    }

    public List<SavedPostResponseDTO> getSavedPostsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec ID : " + userId));

        List<SavedPost> savedPosts = savedPostRepository.findByUser(user);
        return savedPosts.stream()
                .map(SavedPostMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
