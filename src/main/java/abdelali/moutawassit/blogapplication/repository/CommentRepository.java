package abdelali.moutawassit.blogapplication.repository;

import abdelali.moutawassit.blogapplication.model.Comment;
import abdelali.moutawassit.blogapplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Méthode pour récupérer tous les commentaires d’un post donné
    List<Comment> findByPost(Post post);
}