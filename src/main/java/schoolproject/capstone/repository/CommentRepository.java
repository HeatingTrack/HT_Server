package schoolproject.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import schoolproject.capstone.dto.response.CommentResponseDto;
import schoolproject.capstone.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {
    @Query("select new schoolproject.capstone.dto.response.CommentResponseDto(c.id, c.user.name, c.comment) from Comment c where c.board.id = :board_id order by c.createdAt")
    List<CommentResponseDto> comments(@Param("board_id") String boardId);
}
