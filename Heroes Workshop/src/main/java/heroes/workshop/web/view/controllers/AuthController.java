package heroes.workshop.web.view.controllers;

import heroes.workshop.service.models.auth.UserRegisterServiceModel;
import heroes.workshop.service.services.AuthService;
import heroes.workshop.web.view.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;
    private final ModelMapper mapper;

    public AuthController(
            AuthService authService,
            ModelMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public String getLoginForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }

        return "auth/login.html";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("model", new UserRegisterModel());
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserRegisterModel model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register.html";
        }

        UserRegisterServiceModel serviceModel = mapper.map(model, UserRegisterServiceModel.class);
        authService.register(serviceModel);
        return "redirect:/";
    }

}
