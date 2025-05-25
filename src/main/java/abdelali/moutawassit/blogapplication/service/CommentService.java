package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.CommentReactionResponseDTO;
import abdelali.moutawassit.blogapplication.dto.CommentRequestDTO;
import abdelali.moutawassit.blogapplication.dto.CommentResponseDTO;
import abdelali.moutawassit.blogapplication.exception.ResourceNotFoundException;
import abdelali.moutawassit.blogapplication.mapper.CommentMapper;
import abdelali.moutawassit.blogapplication.model.Comment;
import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.CommentRepository;
import abdelali.moutawassit.blogapplication.repository.PostRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private  final CommentReactionService commentReactionService;

    // Créer un commentaire lié à un user et un post
    public CommentResponseDTO createComment(CommentRequestDTO dto, Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + userId));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post non trouvé avec l'id: " + postId));

        Comment comment = CommentMapper.toEntity(dto, user, post);
        Comment saved = commentRepository.save(comment);

        return CommentMapper.toResponseDTO(saved);
    }

    // Récupérer un commentaire par son id
    public CommentResponseDTO getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Commentaire non trouvé avec l'id: " + commentId));

        List<CommentReactionResponseDTO> reactions = commentReactionService.getReactionsForComment(comment.getId());
        int nombreReaction = reactions.size();
        comment.setLikeCount(nombreReaction);
        return CommentMapper.toResponseDTO(comment);
    }

    // Récupérer tous les commentaires d’un post
    public List<CommentResponseDTO> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post non trouvé avec l'id: " + postId));

        return commentRepository.findByPost(post)
                .stream()
                .map(CommentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Mettre à jour un commentaire (exemple : contenu)
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Commentaire non trouvé avec l'id: " + commentId));

        if (dto.getContent() != null) {
            comment.setContent(dto.getContent());
        }
        // Autres mises à jour si besoin

        Comment updated = commentRepository.save(comment);
        return CommentMapper.toResponseDTO(updated);
    }

    // Supprimer un commentaire
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Commentaire non trouvé avec l'id: " + commentId);
        }
        commentRepository.deleteById(commentId);
    }
}
