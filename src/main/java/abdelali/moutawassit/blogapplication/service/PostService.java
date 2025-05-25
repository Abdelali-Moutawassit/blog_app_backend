package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.PostRequestDTO;
import abdelali.moutawassit.blogapplication.dto.PostResponseDTO;
import abdelali.moutawassit.blogapplication.mapper.PostMapper;
import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.PostRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // Créer un post
    public PostResponseDTO createPost(PostRequestDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + userId));

        Post post = PostMapper.toEntity(dto, user);
        Post savedPost = postRepository.save(post);
        return PostMapper.toResponseDTO(savedPost);
    }

    // Récupérer un post par son id
    public PostResponseDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + postId));
        return PostMapper.toResponseDTO(post);
    }

    // Récupérer tous les posts
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Mettre à jour un post (par exemple le contenu)
    public PostResponseDTO updatePost(Long postId, PostRequestDTO dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + postId));

        if (dto.getContent() != null) {
            post.setContent(dto.getContent());
        }
        if (dto.getImageUrl() != null) {
            post.setImageUrl(dto.getImageUrl());
        }
        // On peut gérer d'autres champs selon besoin

        Post updatedPost = postRepository.save(post);
        return PostMapper.toResponseDTO(updatedPost);
    }

    // Supprimer un post
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post not found with id " + postId);
        }
        postRepository.deleteById(postId);
    }
}
