package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
