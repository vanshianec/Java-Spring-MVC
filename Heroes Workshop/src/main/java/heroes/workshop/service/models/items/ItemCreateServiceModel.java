package heroes.workshop.service.models.items;

import heroes.workshop.data.models.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateServiceModel {

    private String name;
    private Slot slot;
    private int attack;
    private int defence;
    private int stamina;
    private int strength;
}
