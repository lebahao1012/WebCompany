package com.company.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.web.entity.User;
import com.company.web.repository.UserRepository;
import com.company.web.service.UserRegistrationService;

@Controller
public class UserRegistrationController {
    @Autowired(required = true)
    UserRegistrationService userRegistrationService;
    UserRepository userRepository;

    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("registerUser")
    public String registerUser(@ModelAttribute("user") User user) {
        userRegistrationService.registerUser(user);
        return "redirect:/";
    }
}
