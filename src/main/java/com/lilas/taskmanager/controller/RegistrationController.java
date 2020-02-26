package com.lilas.taskmanager.controller;

import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.domain.UserRole;
import com.lilas.taskmanager.repo.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRoles", UserRole.values());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user, Model model) {
        User user1 = userRepo.findByUsername(user.getUsername());
        if (user1 != null) {
            model.addAttribute("message", "User Already Exists");
            return "register";

        }
        user.setActive(true);
        user.setUserRoles(Collections.singleton(UserRole.MANAGER));
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String general() {
        return "greeting";
    }
}
