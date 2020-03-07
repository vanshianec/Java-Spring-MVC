package heroes.workshop.service.models.heroes;

import heroes.workshop.data.models.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroDetailsServiceModel {

    private String name;
    private Gender gender;
    private int level;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;

    private HeroItemServiceModel weapon;
    private HeroItemServiceModel pads;
    private HeroItemServiceModel gauntlets;
    private HeroItemServiceModel pauldrons;
    private HeroItemServiceModel helmet;

}
