package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Comment;
import abdelali.moutawassit.blogapplication.model.CommentReaction;
import abdelali.moutawassit.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
    List<CommentReaction> findByComment(Comment comment);
    List<CommentReaction> findByUser(User user);
}
