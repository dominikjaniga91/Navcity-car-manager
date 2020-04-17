package pl.com.navcity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.navcity.model.Authorities;
import pl.com.navcity.model.User;

@Controller
public class LoginController {

    @GetMapping("/login-form")
    public String getLoginPage(Model model){
        model.addAttribute("authorities", Authorities.values());
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/")
    public String returnDefaultPage(){

        return "redirect:/api/routes/list";
    }
}
