package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.PostReaction;
import abdelali.moutawassit.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {
    Optional<PostReaction> findByUserAndPost(User user, Post post);
    List<PostReaction> findAllByPost(Post post);
}
