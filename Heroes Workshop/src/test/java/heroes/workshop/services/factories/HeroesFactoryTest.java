package heroes.workshop.services.factories;

import heroes.workshop.data.models.Gender;
import heroes.workshop.data.models.Hero;
import heroes.workshop.service.factories.HeroesFactory;
import heroes.workshop.services.base.ServiceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static heroes.workshop.service.factories.HeroesConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroesFactoryTest extends ServiceTestBase {
    @Autowired
    HeroesFactory factory;

    @Test
    void create_withNameAndGender_shouldReturnHeroWithDefaultProps() {
        String name = "Hero";
        Gender gender = Gender.FEMALE;

        Hero hero = factory.create(name, gender);

        assertEquals(name, hero.getName());
        assertEquals(gender, hero.getGender());
        assertEquals(INITIAL_ATTACK, hero.getAttack());
        assertEquals(INITIAL_DEFENCE, hero.getDefence());
        assertEquals(INITIAL_STAMINA, hero.getStamina());
        assertEquals(INITIAL_STRENGTH, hero.getStrength());
    }
}
