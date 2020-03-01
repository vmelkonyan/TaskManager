package com.lilas.taskmanager.controller;

import com.lilas.taskmanager.constatns.KeyConstants;
import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.domain.UserRole;

import com.lilas.taskmanager.serice.UserService;
import com.lilas.taskmanager.utils.TaskManagerUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(KeyConstants.USER_KEY)
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getUserList(Model model) {
        model.addAttribute("userRoles", UserRole.values());
        model.addAttribute("users", userService.findAll());
        return KeyConstants.USERS_LIST_VIEW_KEY;
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("userRoles", UserRole.values());
        return KeyConstants.EDIT_USER_VIEW_KEY;
    }

    @GetMapping(KeyConstants.ADD_NEW_USER_KEY)
    public String addNewUser(Model model) {
        model.addAttribute("userRoles", UserRole.values());
        return KeyConstants.ADD_NEW_USER_VIEW_KEY;
    }


    @PostMapping(KeyConstants.ADD_NEW_USER_KEY)
    public String addUse(User user, @RequestParam Map<String, String> form, Model model) {
        if (userService.createNewUser(passwordEncoder,user, form, model)) {
            return KeyConstants.MAIN_KEY;
        }
        return KeyConstants.REDIRECT_KEY + KeyConstants.MAIN_KEY;

    }


    @PostMapping()
    public String updateUser(@RequestParam String username,
                             @RequestParam Map<String, String> form,
                             @RequestParam("userId") User user) {
        user.setUsername(username);

        TaskManagerUtils.updateAuthentication(user);

        Set<String> roles = Arrays.stream(UserRole.values()).map(UserRole::name).collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getUserRoles().add(UserRole.valueOf(key));
            }
        }
        userService.save(user);
        return KeyConstants.REDIRECT_KEY + KeyConstants.USER_KEY;
    }

}
