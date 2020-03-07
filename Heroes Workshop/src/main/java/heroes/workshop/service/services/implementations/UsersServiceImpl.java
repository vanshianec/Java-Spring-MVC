package heroes.workshop.service.services.implementations;

import heroes.workshop.data.models.Hero;
import heroes.workshop.data.models.User;
import heroes.workshop.data.repositories.UserRepository;
import heroes.workshop.service.models.heroes.HeroCreateServiceModel;
import heroes.workshop.service.services.HeroesService;
import heroes.workshop.service.services.UsersService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {
    private final HeroesService heroesService;
    private final UserRepository userRepository;

    public UsersServiceImpl(HeroesService heroesService, UserRepository userRepository) {
        this.heroesService = heroesService;
        this.userRepository = userRepository;
    }

    @Override
    public void createHeroForUser(String username, HeroCreateServiceModel heroServiceModel) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user.getHero() != null) {
            throw new Exception("User already has a hero");
        }

        Hero hero = heroesService.create(heroServiceModel);
        user.setHero(hero);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        Set<GrantedAuthority> authorities = new HashSet<>(user.getAuthorities());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
