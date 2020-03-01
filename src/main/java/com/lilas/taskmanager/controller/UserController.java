package com.lilas.taskmanager.controller;

import com.lilas.taskmanager.constatns.KeyConstants;
import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.domain.UserRole;
import com.lilas.taskmanager.exception.AppException;
import com.lilas.taskmanager.serice.UserService;
import com.lilas.taskmanager.utils.TaskManagerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping(KeyConstants.USER_KEY)
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
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
    public String userEditForm(@PathVariable User user, Model model) throws AppException {
        if (user != null) {
            model.addAttribute("editUser", user);
            model.addAttribute("userRoles", UserRole.values());
        } else {
            throw new AppException();
        }
        return KeyConstants.EDIT_USER_VIEW_KEY;
    }

    @GetMapping(KeyConstants.ADD_NEW_USER_KEY)
    public String addNewUser(Model model) {
        model.addAttribute("userRoles", UserRole.values());
        return KeyConstants.ADD_NEW_USER_VIEW_KEY;
    }


    @PostMapping(KeyConstants.ADD_NEW_USER_KEY)
    public String addUse(User user, @RequestParam Map<String, String> form, Model model) {
        if (userService.createNewUser(passwordEncoder, user, form, model)) {
            return KeyConstants.ADD_NEW_USER_VIEW_KEY;
        }
        LOGGER.info("user correct added user name  is {} ", user.getUsername());
        return KeyConstants.REDIRECT_KEY + KeyConstants.USER_KEY;

    }


    @PostMapping()
    public String updateUser(@RequestParam String username,
                             @RequestParam Map<String, String> form,
                             @RequestParam("userId") User user) {
        user.setUsername(username);


        TaskManagerUtils.updateAuthentication(user);

        Set<String> roles = Arrays.stream(UserRole.values()).map(UserRole::name).collect(Collectors.toSet());

        if (form.get("userRol") != null) {
            user.getUserRoles().clear();
            user.getUserRoles().add(UserRole.valueOf(form.get("userRol")));
            user.setCurrentUserRole(UserRole.valueOf(form.get("userRol")));
        }

        userService.save(user);
        LOGGER.info("user correct updated is {} ", user.getUsername());
        return KeyConstants.REDIRECT_KEY + KeyConstants.USER_KEY;
    }

    @ExceptionHandler(AppException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "User not Found");
        mav.setViewName(KeyConstants.ERROR_VIEW_KEY);
        return mav;
    }
}
