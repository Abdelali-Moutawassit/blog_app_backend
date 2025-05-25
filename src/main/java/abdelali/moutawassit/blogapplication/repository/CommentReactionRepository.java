package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.CommentReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
}
