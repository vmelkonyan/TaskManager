package com.lilas.taskmanager.serice;

import com.lilas.taskmanager.domain.Task;
import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.repo.TaskRepo;
import com.lilas.taskmanager.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskService(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public Iterable<Task> findAll() {
        return taskRepo.findAll();
    }

    public List<Task> findAllByTaskName(String filter) {
        return taskRepo.findAllByTaskName(filter);
    }

    public List<Task> findAllByAuthorAndTaskName(User user, String filter) {
        return taskRepo.findAllByAuthorAndTaskName(user, filter);
    }

    public List<Task> findAllByAuthor(User user) {
        return taskRepo.findAllByAuthor(user);

    }

    public void save(Task task) {
        taskRepo.save(task);
    }
}
