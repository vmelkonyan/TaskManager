package com.lilas.taskmanager.serice;

import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.domain.UserRole;
import com.lilas.taskmanager.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        }
        return user;
    }

    public boolean createNewUser(PasswordEncoder passwordEncoder ,User user, @RequestParam Map<String, String> form, Model model) {
        User userFromDB = findByUsername(user.getUsername());
        if (userFromDB != null) {
            model.addAttribute("usernameError", "User Already Exists");
            model.addAttribute("userRoles", UserRole.values());
            return true;
        }
        if (form.get("userRol") != null) {
            user.setUserRoles(Collections.singleton(UserRole.valueOf(form.get("userRol"))));
        }

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);
        return false;
    }


    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public User findByUsername(String taskOwner) {
        return userRepo.findByUsername(taskOwner);
    }

    public void save(User user) {
        userRepo.save(user);
    }
}
