package heroes.workshop.web.view.controllers;

import heroes.workshop.data.models.Gender;
import heroes.workshop.data.models.Hero;
import heroes.workshop.data.repositories.HeroesRepository;
import heroes.workshop.web.base.ViewTestBase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HeroesControllerTest extends ViewTestBase {
    @MockBean
    HeroesRepository mockHeroesRepository;

    @Test
    void getDetails_whenNoHeroWithName_shouldReturnErrorViewWith404() throws Exception {
        String heroName = "pesho";
        mockMvc.perform(get("/heroes/details/" + heroName))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"));
    }

    @Test
    void getDetails_whenHeroWithName_shouldReturnHeroDetailsViewWith200() throws Exception {
        String heroName = "pesho";
        Hero hero = new Hero();
        hero.setName(heroName);
        hero.setGender(Gender.MALE);
        hero.setItems(new ArrayList<>());

        Mockito.when(mockHeroesRepository.getByNameIgnoreCase(heroName))
                .thenReturn(Optional.of(hero));

        mockMvc.perform(get("/heroes/details/" + heroName))
                .andExpect(status().isOk())
                .andExpect(view().name(HeroesController.HEROES_HERO_DETAILS_VIEW_NAME));
    }
}
