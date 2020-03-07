package heroes.workshop.service.services.validation;

import heroes.workshop.service.models.items.ItemCreateServiceModel;

public interface ItemsValidationService {
    boolean isValid(ItemCreateServiceModel serviceModel);
}
