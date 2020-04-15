package pl.com.navcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import pl.com.navcity.model.User;
import pl.com.navcity.repository.UserRepositoryImpl;
import pl.com.navcity.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public String addUser(User user){

        userService.saveUser(user);
        return "redirect:/login-form";
    }
}
