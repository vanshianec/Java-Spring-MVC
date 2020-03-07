package heroes.workshop.service.models.heroes;

import heroes.workshop.data.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroCreateServiceModel {
    private String name;
    private Gender gender;
}
