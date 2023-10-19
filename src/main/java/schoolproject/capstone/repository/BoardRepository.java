package schoolproject.capstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import schoolproject.capstone.dto.response.BoardListResponseDto;
import schoolproject.capstone.model.Board;

public interface BoardRepository extends JpaRepository<Board, String> {
    @Query("select new schoolproject.capstone.dto.response.BoardListResponseDto(b.num, b.title, u.name, b.createdAt) from Board b join b.user u")
    Page<BoardListResponseDto> findAllBoardList(Pageable pageable);
}
