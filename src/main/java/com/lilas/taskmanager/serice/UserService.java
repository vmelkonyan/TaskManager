package com.lilas.taskmanager.serice;

import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
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
