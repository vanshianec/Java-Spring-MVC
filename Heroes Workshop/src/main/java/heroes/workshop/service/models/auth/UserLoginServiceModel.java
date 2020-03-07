package heroes.workshop.service.models.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginServiceModel {

    private String username;
    private String heroName;

    public UserLoginServiceModel(String username, String heroName) {
        this.username = username;
        this.heroName = heroName;
    }

}
