//package abdelali.moutawassit.blogapplication.service;
//
//import abdelali.moutawassit.blogapplication.dto.PostReactionResponseDTO;
//import abdelali.moutawassit.blogapplication.dto.PostShareRequestDTO;
//import abdelali.moutawassit.blogapplication.dto.PostShareResponseDTO;
//import abdelali.moutawassit.blogapplication.mapper.PostReactionMapper;
//import abdelali.moutawassit.blogapplication.mapper.PostShareMapper;
//import abdelali.moutawassit.blogapplication.model.Post;
//import abdelali.moutawassit.blogapplication.model.PostShare;
//import abdelali.moutawassit.blogapplication.model.User;
//import abdelali.moutawassit.blogapplication.repository.PostRepository;
//import abdelali.moutawassit.blogapplication.repository.PostShareRepository;
//import abdelali.moutawassit.blogapplication.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class PostShareService {
//
//    private final PostRepository postRepository;
//    private final UserRepository userRepository;
//    private final PostShareRepository postShareRepository;
//
//    @Transactional
//    public PostShareResponseDTO addShare(Long userId, Long postId, PostShareRequestDTO dto) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
//
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post introuvable"));
//
//        // Vérifie si déjà partagé
//        Optional<PostShare> existingShare = postShareRepository.findByUserAndPost(user, post);
//        if (existingShare.isPresent()) {
//            throw new RuntimeException("L'utilisateur a déjà partagé ce post.");
//        }
//
//        PostShare newShare = PostShareMapper.toEntity(dto, user, post);
//        PostShare savedShare = postShareRepository.save(newShare);
//
//        post.setShareCount(post.getShareCount() + 1);
//        postRepository.save(post);
//
//        return PostShareMapper.toResponseDTO(savedShare);
//    }
//
//    @Transactional
//    public void deleteShare(Long userId, Long postId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
//
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new RuntimeException("Post introuvable"));
//
//        PostShare share = postShareRepository.findByUserAndPost(user, post)
//                .orElseThrow(() -> new RuntimeException("Partage introuvable"));
//
//        postShareRepository.delete(share);
//        post.setShareCount(post.getShareCount() - 1);
//        postRepository.save(post);
//    }
//}
