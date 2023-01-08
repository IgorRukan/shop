package com.example.springlog.controller;

import com.example.springlog.model.User;
import com.example.springlog.service.SecurityService;
import com.example.springlog.service.UserService;
import com.example.springlog.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    private Logger logger = Logger.getLogger(getClass().getName());
    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }
        String username = userForm.getUsername();
        User existingUser = userService.findByUsername(username);
        if (existingUser != null) {
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "User already exist in database");

            logger.warning("User name already exists.");


            return "register";
        }
        userService.save(userForm);
        //securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(User user) {
        return "login";
    }

    @PostMapping("/delete")
    public String DeleteUser(@RequestParam(value = "id") String id){
        userService.deleteUser(id);
        return "home";
    }
    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("user",userService.findByUsername(userService.getCurrentUsername()));
        return "profile";
    }
}
