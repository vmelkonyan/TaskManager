package com.lilas.taskmanager.repo;

import com.lilas.taskmanager.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
