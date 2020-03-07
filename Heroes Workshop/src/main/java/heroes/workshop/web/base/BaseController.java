package heroes.workshop.web.base;

import heroes.workshop.service.models.auth.UserLoginServiceModel;

import javax.servlet.http.HttpSession;

public class BaseController {
    protected String getUsername(HttpSession session) {
        return ((UserLoginServiceModel) session.getAttribute("user")).getUsername();
    }

    protected String getHeroName(HttpSession session) {
        return ((UserLoginServiceModel) session.getAttribute("user")).getHeroName();
    }
}
