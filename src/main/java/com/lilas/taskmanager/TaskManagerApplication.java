package com.lilas.taskmanager;

import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.domain.UserRole;
import com.lilas.taskmanager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    public TaskManagerApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

    @Override
    public void run(String... args) {

        User user = new User("admin", passwordEncoder.encode("admin"));
        user.setUserRoles(Collections.singleton(UserRole.MANAGER));
        user.setActive(true);
        userRepo.save(user);

    }

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


}
