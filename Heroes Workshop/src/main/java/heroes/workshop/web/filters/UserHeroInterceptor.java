package heroes.workshop.web.filters;

import heroes.workshop.errors.HeroNotFoundException;
import heroes.workshop.service.models.heroes.HeroDetailsServiceModel;
import heroes.workshop.service.services.AuthenticatedUserService;
import heroes.workshop.service.services.HeroesService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class UserHeroInterceptor implements HandlerInterceptor {
    private final AuthenticatedUserService authenticatedUserService;
    private final HeroesService heroesService;

    public UserHeroInterceptor(
            AuthenticatedUserService authenticatedUserService,
            HeroesService heroesService) {

        this.authenticatedUserService = authenticatedUserService;
        this.heroesService = heroesService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String username = authenticatedUserService.getUsername();
        try {
            HeroDetailsServiceModel hero = heroesService.getByUsername(username);
            request.getSession()
                    .setAttribute("heroName", hero.getName());
        } catch (HeroNotFoundException ex) {
            // do nothing
        }
    }
}