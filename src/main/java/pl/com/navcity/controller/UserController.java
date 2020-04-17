package pl.com.navcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.navcity.model.User;
import pl.com.navcity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public String addUser(@Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "login";
        }
        userService.saveUser(user);
        return "redirect:/login-form";
    }
}
