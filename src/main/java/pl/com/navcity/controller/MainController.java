package pl.com.navcity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String returnDefaultPage(){

        return "redirect:/api/routes/list";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage(){

        return "accessDenied";
    }
}
