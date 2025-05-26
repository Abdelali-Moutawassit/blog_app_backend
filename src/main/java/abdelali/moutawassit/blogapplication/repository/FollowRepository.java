package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Follow;
import abdelali.moutawassit.blogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowerAndFollowee(User follower, User followee);
}
