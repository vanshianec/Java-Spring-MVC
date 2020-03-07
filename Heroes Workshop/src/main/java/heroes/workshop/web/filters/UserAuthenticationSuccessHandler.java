package heroes.workshop.web.filters;

import heroes.workshop.errors.HeroNotFoundException;
import heroes.workshop.service.models.heroes.HeroDetailsServiceModel;
import heroes.workshop.service.services.AuthenticatedUserService;
import heroes.workshop.service.services.HeroesService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthenticatedUserService authenticatedUserService;
    private final HeroesService heroesService;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public UserAuthenticationSuccessHandler(
            AuthenticatedUserService authenticatedUserService,
            HeroesService heroesService) {
        super();
        this.authenticatedUserService = authenticatedUserService;
        this.heroesService = heroesService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        String username = authenticatedUserService.getUsername();
        try {
            HeroDetailsServiceModel hero = heroesService.getByUsername(username);
            httpServletRequest.getSession()
                    .setAttribute("heroName", hero.getName());
        } catch (HeroNotFoundException ex) {
            // do nothing
        }

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home");
    }
}