package heroes.workshop.service.factories.base;

import heroes.workshop.config.annotations.Factory;
import heroes.workshop.data.models.Gender;
import heroes.workshop.data.models.Hero;
import heroes.workshop.service.factories.HeroesFactory;

import static heroes.workshop.service.factories.HeroesConstants.*;

@Factory
public class HeroesFactoryImpl implements HeroesFactory {
    @Override
    public Hero create(String name, Gender gender) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setGender(gender);
        hero.setAttack(INITIAL_ATTACK);
        hero.setDefence(INITIAL_DEFENCE);
        hero.setLevel(INITIAL_LEVEL);
        hero.setStamina(INITIAL_STAMINA);
        hero.setStrength(INITIAL_STRENGTH);

        return hero;
    }
}
