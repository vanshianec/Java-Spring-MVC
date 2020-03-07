package heroes.workshop.services.services;

import heroes.workshop.base.TestBase;
import heroes.workshop.data.models.Gender;
import heroes.workshop.data.models.Hero;
import heroes.workshop.data.models.User;
import heroes.workshop.data.repositories.UserRepository;
import heroes.workshop.service.models.heroes.HeroCreateServiceModel;
import heroes.workshop.service.services.UsersService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsersServiceTest extends TestBase {
    @MockBean
    UserRepository usersRepository;

    @Autowired
    UsersService service;

    @Test
    public void createHeroForUser_whenUserExistsAndDoesNotHaveAHero_shouldCreateHeroForUser() throws Exception {
        User user = new User();
        user.setUsername("Pesho");
        String heroName = "Gosho";
        Mockito.when(usersRepository.findByUsername(user.getUsername()))
                .thenReturn(user);

        HeroCreateServiceModel heroToCreate = new HeroCreateServiceModel(heroName, Gender.MALE);


        service.createHeroForUser(user.getUsername(), heroToCreate);

        assertEquals(heroName, user.getHero().getName());
    }

    public void createHeroForUser_whenUserDoesNOTExist_shouldThrowException() {
    }

    @Test
    public void createHeroForUser_whenUserExistsAndHasAHero_shouldThrowException() {
        User user = new User();
        user.setUsername("Pesho");
        user.setHero(new Hero());
        String heroName = "Gosho";
        Mockito.when(usersRepository.findByUsername(user.getUsername()))
                .thenReturn(user);

        HeroCreateServiceModel heroToCreate = new HeroCreateServiceModel(heroName, Gender.MALE);

        assertThrows(Exception.class, () ->
                service.createHeroForUser(user.getUsername(), heroToCreate));
    }
}
