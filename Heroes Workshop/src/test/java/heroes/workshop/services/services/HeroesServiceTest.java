package heroes.workshop.services.services;

import heroes.workshop.base.TestBase;
import heroes.workshop.data.models.Hero;
import heroes.workshop.data.repositories.HeroesRepository;
import heroes.workshop.errors.HeroNotFoundException;
import heroes.workshop.service.models.heroes.HeroDetailsServiceModel;
import heroes.workshop.service.services.HeroesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeroesServiceTest extends TestBase {
    @MockBean
    HeroesRepository heroesRepository;

    @Autowired
    HeroesService service;

    @Test
    void getByName_whenHeroDoesNotExist_shouldThrowHeroNotFoundException() {
        String heroName = "Hero Name";

        Mockito.when(heroesRepository.getByNameIgnoreCase(heroName))
                .thenReturn(Optional.empty());

        assertThrows(
                HeroNotFoundException.class,
                () -> service.getByName(heroName));
    }

    @Test
    void getByName_whenHeroDoesExist_shouldReturnHero() {
        String heroName = "Hero name";

        Hero hero = new Hero();
        hero.setName(heroName);
        hero.setItems(new ArrayList<>());

        Mockito.when(heroesRepository.getByNameIgnoreCase(heroName))
                .thenReturn(Optional.of(hero));

        HeroDetailsServiceModel heroDetails = service.getByName(heroName);

        assertEquals(hero.getName(), heroDetails.getName());
    }

    @Test
    void levelUp_whenHeroWon_shouldReturnCorrectLevel() {
        Hero hero = new Hero();
        hero.setName("Pesho");

        service.levelUp(hero);
        assertEquals(hero.getLevel(), 1);
    }
}
