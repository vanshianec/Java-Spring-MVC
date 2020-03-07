package heroes.workshop.services.services.validation;

import heroes.workshop.base.TestBase;
import heroes.workshop.data.models.Slot;
import heroes.workshop.service.models.items.ItemCreateServiceModel;
import heroes.workshop.service.services.validation.ItemsValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemsValidationServiceTest extends TestBase {
    @Autowired
    ItemsValidationService service;

    @Test
    void isValid_whenNameIsNull_shouldReturnFalse() {
        ItemCreateServiceModel serviceModel = new ItemCreateServiceModel(null, Slot.PADS, 1, 2, 3, 4);
        boolean isValid = service.isValid(serviceModel);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenSlotIsNull_shouldReturnFalse() {
        ItemCreateServiceModel serviceModel = new ItemCreateServiceModel("Valid name", null, 1, 2, 3, 4);
        boolean isValid = service.isValid(serviceModel);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenAttackIsNegative_shouldReturnFalse() {
        ItemCreateServiceModel serviceModel = new ItemCreateServiceModel("Valid name", Slot.PADS, -1, 2, 3, 4);
        boolean isValid = service.isValid(serviceModel);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenItemIsValid_shouldReturnTrue() {
        ItemCreateServiceModel serviceModel = new ItemCreateServiceModel("Valid name", Slot.PADS, 1, 2, 3, 4);
        boolean isValid = service.isValid(serviceModel);
        assertTrue(isValid);
    }
}
