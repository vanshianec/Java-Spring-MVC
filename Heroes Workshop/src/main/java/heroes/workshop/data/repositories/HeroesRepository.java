package heroes.workshop.data.repositories;

import heroes.workshop.data.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroesRepository extends JpaRepository<Hero, Long> {

    Optional<Hero> getByNameIgnoreCase(String name);

    Optional<Hero> getByUserUsername(String username);

}
