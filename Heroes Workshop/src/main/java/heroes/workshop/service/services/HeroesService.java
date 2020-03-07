package heroes.workshop.service.services;

import heroes.workshop.data.models.Hero;
import heroes.workshop.service.models.heroes.HeroCreateServiceModel;
import heroes.workshop.service.models.heroes.HeroDetailsServiceModel;

import java.util.List;

public interface HeroesService {
    HeroDetailsServiceModel getByName(String name);

    Hero create(HeroCreateServiceModel serviceModel);

    boolean areThereOpponents();

    List<HeroDetailsServiceModel> getOpponents(String heroName);

    String getWinner(HeroDetailsServiceModel player1, HeroDetailsServiceModel player2);

    void levelUp(Hero winner);

    HeroDetailsServiceModel getByUsername(String username);

    void levelUpHeroes();
}
