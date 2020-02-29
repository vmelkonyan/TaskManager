package com.lilas.taskmanager.controller;

import com.lilas.taskmanager.constatns.KeyConstants;
import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.domain.UserRole;
import com.lilas.taskmanager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {

    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(KeyConstants.REGISTER_KEY)
    public String register(Model model) {
        model.addAttribute("userRoles", UserRole.values());
        model.addAttribute("messageaa", "User Already Exists");
        return KeyConstants.REGISTER_VIEW_KEY;
    }

    @PostMapping(KeyConstants.REGISTER_KEY)
    public String addUse( User user, @RequestParam Map<String, String> form, Model model) {
        User user1 = userRepo.findByUsername(user.getUsername());
        if (user1 != null) {
            model.addAttribute("messageaa", "User Already Exists");
            return KeyConstants.REGISTER_VIEW_KEY;

        }
        Set<String> roles = Arrays.stream(UserRole.values()).map(UserRole::name).collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.setUserRoles(Collections.singleton(UserRole.valueOf(key)));
            }
        }
        model.addAttribute("messageaa", "User Already Exists");
        user.setActive(true);
        userRepo.save(user);
        return KeyConstants.REDIRECT_KEY + KeyConstants.MAIN_KEY;

    }

    @GetMapping("/")
    public String general() {
        return "greeting";
    }
}
