package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.PostReaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {
}
