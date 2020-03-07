package heroes.workshop.service.services;

import heroes.workshop.service.models.items.ItemCreateServiceModel;
import heroes.workshop.service.models.items.ItemServiceModel;

import java.util.List;

public interface ItemsService {
    List<ItemServiceModel> getItemsForUser(String username);

    void addToUserById(long id, String username);

    void create(ItemCreateServiceModel serviceModel);

    List<ItemServiceModel> getAll();
}
