package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
