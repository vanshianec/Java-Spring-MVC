package heroes.workshop.data.repositories;

import heroes.workshop.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

}
