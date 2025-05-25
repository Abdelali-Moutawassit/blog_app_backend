package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Post;
import abdelali.moutawassit.blogapplication.model.SavedPost;
import abdelali.moutawassit.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedPostRepository extends JpaRepository<SavedPost, Long> {
    List<SavedPost> findByUser(User user);
    Optional<SavedPost> findByUserAndPost(User user, Post post);
    boolean existsByUserAndPost(User user, Post post);
}
