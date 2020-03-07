package heroes.workshop.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterModel {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
