package heroes.workshop.service.services.validation;

import heroes.workshop.service.models.auth.UserRegisterServiceModel;

public interface AuthValidationService {
    boolean isValid(UserRegisterServiceModel model);
}
