package heroes.workshop.service.services;

import heroes.workshop.service.models.auth.UserRegisterServiceModel;

public interface AuthService {

    void register(UserRegisterServiceModel model);

}
