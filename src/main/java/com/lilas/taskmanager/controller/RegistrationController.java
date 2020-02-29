package com.lilas.taskmanager.controller;

import com.lilas.taskmanager.constatns.KeyConstants;
import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.domain.UserRole;
import com.lilas.taskmanager.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepo userRepo;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @GetMapping(KeyConstants.REGISTER_KEY)
    public String register(Model model) {
        model.addAttribute("userRoles", UserRole.values());
        return KeyConstants.REGISTER_VIEW_KEY;
    }

    @PostMapping(KeyConstants.REGISTER_KEY)
    public String addUse(User user, @RequestParam Map<String, String> form, Model model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if (userFromDB != null) {
            model.addAttribute("usernameError", "User Already Exists");
            model.addAttribute("userRoles", UserRole.values());
            return KeyConstants.REGISTER_VIEW_KEY;
        }
        if (form.get("userRol") != null) {
            user.setUserRoles(Collections.singleton(UserRole.valueOf(form.get("userRol"))));
        }

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return KeyConstants.REDIRECT_KEY + KeyConstants.MAIN_KEY;

    }

    @GetMapping("/")
    public String general() {
        return KeyConstants.SPLASH_KEY;
    }
}
