package heroes.workshop.service.services.validation.implementations;

import heroes.workshop.data.repositories.UserRepository;
import heroes.workshop.service.models.auth.UserRegisterServiceModel;
import heroes.workshop.service.services.validation.AuthValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final UserRepository userRepository;

    @Autowired
    public AuthValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(UserRegisterServiceModel model) {
        return isEmailValid(model.getEmail())
                && arePasswordsMatching(model.getPassword(), model.getConfirmPassword())
                && !isUsernameTaken(model.getUsername());
    }

    private boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isUsernameTaken(String username) {
        return this.userRepository.existsByUsername(username);
    }
}
