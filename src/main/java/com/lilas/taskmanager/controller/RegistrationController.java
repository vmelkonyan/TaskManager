package com.lilas.taskmanager.controller;

import com.lilas.taskmanager.constatns.KeyConstants;
import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.domain.UserRole;
import com.lilas.taskmanager.serice.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private static final Logger LOGGER= LoggerFactory.getLogger(RegistrationController.class);
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


    public RegistrationController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping(KeyConstants.REGISTER_KEY)
    public String register(Model model) {
        model.addAttribute("userRoles", UserRole.values());
        return KeyConstants.REGISTER_VIEW_KEY;
    }

    @PostMapping(KeyConstants.REGISTER_KEY)
    public String addUse(User user, @RequestParam Map<String, String> form, Model model) {
        if (userService.createNewUser(passwordEncoder, user, form, model)) {
            return KeyConstants.ADD_NEW_USER_VIEW_KEY;
        }
        LOGGER.info("User correct add");
        return KeyConstants.REDIRECT_KEY + KeyConstants.MAIN_KEY;

    }

    @RequestMapping(value = KeyConstants.LOGIN_KEY, method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect";
        }
        model.addAttribute("errorMessage", errorMessge);
        return KeyConstants.LOGIN_VIEW_KEY;
    }

    @GetMapping("/")
    public String general() {
        return KeyConstants.SPLASH_KEY;
    }
}
