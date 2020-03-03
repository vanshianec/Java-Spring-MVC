package heroes.workshop.web.controllers;

import heroes.workshop.service.models.auth.UserRegisterServiceModel;
import heroes.workshop.service.services.AuthService;
import heroes.workshop.web.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "auth/login.html";
    }

    @GetMapping("/register")
    public String getRegisterForm(){
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegisterModel model){
        //TODO do with boolean method
        authService.register(this.modelMapper.map(model, UserRegisterServiceModel.class));
        return "redirect:/";
    }

}
