package heroes.workshop.service.services.validation.implementations;

import heroes.workshop.service.models.items.ItemCreateServiceModel;
import heroes.workshop.service.services.validation.ItemsValidationService;
import org.springframework.stereotype.Service;

@Service
public class ItemsValidationServiceImpl implements ItemsValidationService {
    @Override
    public boolean isValid(ItemCreateServiceModel serviceModel) {
        return serviceModel != null &&
                serviceModel.getName() != null &&
                serviceModel.getSlot() != null &&
                serviceModel.getAttack() > 0;
    }
}
