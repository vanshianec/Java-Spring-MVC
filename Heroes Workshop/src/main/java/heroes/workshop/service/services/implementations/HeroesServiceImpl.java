package heroes.workshop.service.services.implementations;

import heroes.workshop.data.models.Hero;
import heroes.workshop.data.models.Item;
import heroes.workshop.data.models.Slot;
import heroes.workshop.data.repositories.HeroesRepository;
import heroes.workshop.errors.HeroNotFoundException;
import heroes.workshop.service.factories.HeroesFactory;
import heroes.workshop.service.models.heroes.HeroCreateServiceModel;
import heroes.workshop.service.models.heroes.HeroDetailsServiceModel;
import heroes.workshop.service.models.heroes.HeroItemServiceModel;
import heroes.workshop.service.services.HeroesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HeroesServiceImpl implements HeroesService {
    private final HeroesRepository heroesRepository;
    private final HeroesFactory heroesFactory;
    private final ModelMapper mapper;

    @Override
    public HeroDetailsServiceModel getByName(String name) {
        Optional<Hero> heroResult = heroesRepository.getByNameIgnoreCase(name);
        if (!heroResult.isPresent()) {
            throw new HeroNotFoundException("Hero with such name does not exist");
        }

        Hero hero = heroResult.get();

        HeroDetailsServiceModel serviceModel = mapper.map(hero, HeroDetailsServiceModel.class);

        serviceModel.setWeapon(getItemBySlot(hero.getItems(), Slot.WEAPON));
        serviceModel.setGauntlets(getItemBySlot(hero.getItems(), Slot.GAUNTLETS));
        serviceModel.setHelmet(getItemBySlot(hero.getItems(), Slot.HELMET));
        serviceModel.setPads(getItemBySlot(hero.getItems(), Slot.PADS));
        serviceModel.setPauldrons(getItemBySlot(hero.getItems(), Slot.PAULDRON));

        return serviceModel;
    }

    @Override
    public Hero create(HeroCreateServiceModel serviceModel) {
        Hero hero = heroesFactory.create(serviceModel.getName(), serviceModel.getGender());
        heroesRepository.save(hero);
        return hero;
    }

    @Override
    public boolean areThereOpponents() {
        return heroesRepository.count() > 1;
    }

    @Override
    public List<HeroDetailsServiceModel> getOpponents(String heroName) {
        return heroesRepository.findAll()
                .stream()
                .filter(hero -> !hero.getName().equals(heroName))
                .map(hero -> mapper.map(hero, HeroDetailsServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getWinner(
            HeroDetailsServiceModel player1,
            HeroDetailsServiceModel player2) {
        int player1Dmg = player1.getAttack() + player1.getStrength() * 4 -
                (player2.getDefence() + player2.getStamina() * 2);
        int player2Dmg = player2.getAttack() + player2.getStrength() * 4 -
                (player1.getDefence() + player1.getStamina() * 2);

        if (player1Dmg > player2Dmg) {
            levelUp(heroesRepository
                    .getByNameIgnoreCase(player1.getName())
                    .orElseThrow(() -> new HeroNotFoundException("Not such hero")));
            return player1.getName();
        } else {
            levelUp(heroesRepository
                    .getByNameIgnoreCase(player2.getName())
                    .orElseThrow(() -> new HeroNotFoundException("Not such hero")));
            return player2.getName();
        }
    }

    @Override
    public void levelUp(Hero winner) {
        heroesRepository.save(
                levelUpHero(winner)
        );
    }

    @Override
    public HeroDetailsServiceModel getByUsername(String username) {
        Optional<Hero> hero = heroesRepository.getByUserUsername(username);
        if (!hero.isPresent()) {
            throw new HeroNotFoundException("Not such hero");
        }

        return mapper.map(hero.get(), HeroDetailsServiceModel.class);
    }

    @Override
    public void levelUpHeroes() {
        List<Hero> heroes = heroesRepository.findAll()
                .stream()
                .map(this::levelUpHero)
                .collect(Collectors.toList());
        heroesRepository.saveAll(heroes);
    }

    private Hero levelUpHero(Hero hero) {
        hero.setLevel(hero.getLevel() + 1);
        hero.setStamina(hero.getStamina() + 5);
        hero.setStrength(hero.getStrength() + 5);
        return hero;
    }

    private HeroItemServiceModel getItemBySlot(List<Item> items, Slot slot) {
        Optional<Item> item = items
                .stream()
                .filter(x -> x.getSlot() == slot)
                .findFirst();

        return item.isPresent()
                ? mapper.map(item, HeroItemServiceModel.class)
                : null;
    }
}
