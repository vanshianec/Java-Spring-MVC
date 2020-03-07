package heroes.workshop.service.factories;

import heroes.workshop.data.models.Gender;
import heroes.workshop.data.models.Hero;

public interface HeroesFactory {
    Hero create(String name, Gender gender);
}
