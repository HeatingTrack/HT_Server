package schoolproject.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schoolproject.capstone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
