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
public class ItemServiceModel {

    private long id;
    private String name;
    private int stamina;
    private int strength;
    private int attack;
    private int defence;
    private Slot slot;
    private boolean isOwned;
}
