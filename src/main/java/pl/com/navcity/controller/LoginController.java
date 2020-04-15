package pl.com.navcity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.navcity.model.User;

@Controller
public class LoginController {

    @GetMapping("/login-form")
    public String getLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }
}
