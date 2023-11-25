package schoolproject.capstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import schoolproject.capstone.dto.response.BoardDetailResponseDto;
import schoolproject.capstone.dto.response.BoardListResponseDto;
import schoolproject.capstone.model.Board;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, String> {
    @Query("select new schoolproject.capstone.dto.response.BoardListResponseDto(b.num, b.title, u.name, b.createdAt) from Board b join b.user u")
    Page<BoardListResponseDto> findAllBoardList(Pageable pageable);

    @Query("select new schoolproject.capstone.dto.response.BoardDetailResponseDto(b.id, b.title, b.content, b.createdAt, u.name) from Board b join b.user u where b.num = :num")
    Optional<BoardDetailResponseDto> findDetailBoard(@Param("num") Long num);
}
