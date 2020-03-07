package heroes.workshop.web.view.controllers;

import heroes.workshop.service.services.AuthenticatedUserService;
import heroes.workshop.service.services.HeroesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final HeroesService heroesService;
    private final ModelMapper modelMapper;
    private final AuthenticatedUserService authenticatedUserService;

    public HomeController(
            HeroesService heroesService,
            ModelMapper modelMapper,
            AuthenticatedUserService authenticatedUserService) {
        this.heroesService = heroesService;
        this.modelMapper = modelMapper;
        this.authenticatedUserService = authenticatedUserService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "home/index.html";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView) {
        modelAndView.setViewName("home/home");
        return modelAndView;
    }

}
