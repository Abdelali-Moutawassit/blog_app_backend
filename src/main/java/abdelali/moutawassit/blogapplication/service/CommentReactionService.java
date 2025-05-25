package abdelali.moutawassit.blogapplication.service;

import abdelali.moutawassit.blogapplication.dto.CommentReactionRequestDTO;
import abdelali.moutawassit.blogapplication.dto.CommentReactionResponseDTO;
import abdelali.moutawassit.blogapplication.exception.ResourceNotFoundException;
import abdelali.moutawassit.blogapplication.mapper.CommentReactionMapper;
import abdelali.moutawassit.blogapplication.model.Comment;
import abdelali.moutawassit.blogapplication.model.CommentReaction;
import abdelali.moutawassit.blogapplication.model.User;
import abdelali.moutawassit.blogapplication.repository.CommentReactionRepository;
import abdelali.moutawassit.blogapplication.repository.CommentRepository;
import abdelali.moutawassit.blogapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentReactionService {

    private final CommentReactionRepository reactionRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentReactionResponseDTO reactToComment(Long userId, Long commentId, CommentReactionRequestDTO requestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + userId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Commentaire non trouvé avec l'id: " + commentId));

        CommentReaction reaction = CommentReactionMapper.toEntity(requestDTO, user, comment);
        reactionRepository.save(reaction);
        return CommentReactionMapper.toResponseDTO(reaction);
    }

    public void removeReaction(Long reactionId) {
        CommentReaction reaction = reactionRepository.findById(reactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Réaction non trouvée avec l'id: " + reactionId));

        reactionRepository.delete(reaction);
    }

    public List<CommentReactionResponseDTO> getReactionsForComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Commentaire non trouvé avec l'id: " + commentId));

        return reactionRepository.findByComment(comment).stream()
                .map(CommentReactionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<CommentReactionResponseDTO> getReactionsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id: " + userId));

        return reactionRepository.findByUser(user).stream()
                .map(CommentReactionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
