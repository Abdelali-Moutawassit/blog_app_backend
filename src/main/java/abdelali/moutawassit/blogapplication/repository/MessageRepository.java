package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
