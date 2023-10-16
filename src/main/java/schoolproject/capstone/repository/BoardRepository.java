package schoolproject.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schoolproject.capstone.model.Board;

public interface BoardRepository extends JpaRepository<Board, String> {

}
