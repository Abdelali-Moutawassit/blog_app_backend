package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.PostReactionRequestDTO;
import abdelali.moutawassit.blogapplication.dto.PostReactionResponseDTO;
import abdelali.moutawassit.blogapplication.mapper.PostReactionMapper;
import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.PostReaction;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.PostReactionRepository;
import abdelali.moutawassit.blogapplication.repository.PostRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostReactionService {

    private final PostReactionRepository postReactionRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public PostReactionResponseDTO addReaction(Long userId, Long postId, PostReactionRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post introuvable"));

        Optional<PostReaction> existingReaction = postReactionRepository.findByUserAndPost(user, post);

        existingReaction.ifPresent(oldReaction -> {
            if (oldReaction.getType().name().equals("LIKE")) {
                post.setLikeCount(post.getLikeCount() - 1); // enlever ancien like
            }
            postReactionRepository.delete(oldReaction);
        });

        PostReaction newReaction = PostReactionMapper.toEntity(dto, user, post);
        PostReaction savedReaction = postReactionRepository.save(newReaction);

        // si la nouvelle réaction est un LIKE, on incrémente
            post.setLikeCount(post.getLikeCount() + 1);

        postRepository.save(post); // très important

        return PostReactionMapper.toResponseDTO(savedReaction);
    }

//    public PostReactionResponseDTO addReaction(Long userId, Long postId, PostReactionRequestDTO dto) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
//
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post introuvable"));
//
////        post.setLikeCount(post.getLikeCount() + 1);
//
//        // Vérifier si l'utilisateur a déjà réagi à ce post
//        Optional<PostReaction> existingReaction = postReactionRepository.findByUserAndPost(user, post);
//        existingReaction.ifPresent(postReactionRepository::delete); // Supprimer l'ancienne réaction si elle existe
//
//        PostReaction newReaction = PostReactionMapper.toEntity(dto, user, post);
//        PostReaction savedReaction = postReactionRepository.save(newReaction);
//
//        return PostReactionMapper.toResponseDTO(savedReaction);
//    }

    @Transactional
    public void deleteReaction(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post introuvable"));

        PostReaction reaction = postReactionRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new RuntimeException("Réaction introuvable"));

            post.setLikeCount(post.getLikeCount() - 1);

        postReactionRepository.delete(reaction);
        postRepository.save(post); // très important
    }

//    public void deleteReaction(Long userId, Long postId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
//
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post introuvable"));
//
//        PostReaction reaction = postReactionRepository.findByUserAndPost(user, post)
//                .orElseThrow(() -> new RuntimeException("Réaction introuvable"));
//
//        postReactionRepository.delete(reaction);
//    }

    public List<PostReactionResponseDTO> getReactionsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post introuvable"));

        return postReactionRepository.findAllByPost(post).stream()
                .map(PostReactionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Map<String, Long> countReactionsByType(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post introuvable"));

        List<PostReaction> reactions = postReactionRepository.findAllByPost(post);

        return reactions.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getType().name(),
                        Collectors.counting()
                ));
    }
}
