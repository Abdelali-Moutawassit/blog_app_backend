package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.SavedPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedPostRepository extends JpaRepository<SavedPost, Long> {
}
