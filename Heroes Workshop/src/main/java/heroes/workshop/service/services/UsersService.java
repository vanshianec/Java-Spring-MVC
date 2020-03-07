package heroes.workshop.service.services;

import heroes.workshop.service.models.heroes.HeroCreateServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    void createHeroForUser(String username, HeroCreateServiceModel hero) throws Exception;
}
