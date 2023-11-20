package schoolproject.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schoolproject.capstone.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {

}
