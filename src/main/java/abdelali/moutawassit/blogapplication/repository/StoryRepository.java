package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findByExpiresAtAfter(LocalDateTime now);

    List<Story> findByUserIdAndExpiresAtAfter(Long userId, LocalDateTime now);

    List<Story> findByExpiresAtBefore(LocalDateTime now);
}