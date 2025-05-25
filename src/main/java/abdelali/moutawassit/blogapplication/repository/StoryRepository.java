package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
