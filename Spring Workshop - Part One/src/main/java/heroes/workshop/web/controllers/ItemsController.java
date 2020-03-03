package heroes.workshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @GetMapping("/create")
    public String getCreateItemForm(){
        return "items/create-item.html";
    }

    @GetMapping("/merchant")
    public String getMerchant(){
        return "items/merchant.html";
    }

}
