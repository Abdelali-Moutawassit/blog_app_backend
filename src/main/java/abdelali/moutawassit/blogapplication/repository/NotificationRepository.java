package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
