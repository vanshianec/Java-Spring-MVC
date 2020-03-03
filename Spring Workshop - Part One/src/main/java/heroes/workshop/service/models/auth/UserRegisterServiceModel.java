package heroes.workshop.service.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterServiceModel {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;

}
