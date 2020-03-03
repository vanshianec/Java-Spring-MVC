package heroes.workshop.service.services.implementations;

import heroes.workshop.data.models.User;
import heroes.workshop.data.repositories.UserRepository;
import heroes.workshop.service.models.auth.UserRegisterServiceModel;
import heroes.workshop.service.services.AuthService;
import heroes.workshop.service.services.HashingService;
import heroes.workshop.service.services.validation.AuthValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthValidationService validationService;
    private final HashingService hashingService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthValidationService validationService, HashingService hashingService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
        this.hashingService = hashingService;
    }

    @Override
    public void register(UserRegisterServiceModel model) {
        if (!validationService.isValid(model)) {
            //TODO notify the user
            return;
        }

        User user = this.modelMapper.map(model, User.class);
        user.setPassword(this.hashingService.hash(user.getPassword()));
        this.userRepository.save(user);
    }
}
