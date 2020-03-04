package com.lilas.taskmanager.serice;

import com.lilas.taskmanager.domain.Task;
import com.lilas.taskmanager.domain.TaskStatus;
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

    public void deleteTsk(Task task){
        taskRepo.delete(task);
    }

    public Iterable<Task> findAll() {
        return taskRepo.findAll();
    }

    public List<Task> findAllByTaskNameContaining(String filter) {
        return taskRepo.findAllByTaskNameContaining(filter);
    }

    public List<Task> findAllByAssigneeAndTaskNameContaining(User user, String filter) {
        return taskRepo.findAllByAssigneeAndTaskNameContaining(user, filter);
    }

    public List<Task> findAllByAssigneeContainingAndTaskNameContaining(User user, String filter) {
        return taskRepo.findAllByAssigneeContainingAndTaskNameContaining(user, filter);
    }
    public List<Task> findAllByAssigneeContainingAndTaskNameContainingAndTaskStatus(User user, String filter, TaskStatus taskStatus) {
        return taskRepo.findAllByAssigneeContainingAndTaskNameContainingAndTaskStatus(user, filter,taskStatus);
    }

    public List<Task> findAllByAssignee(User user) {
        return taskRepo.findAllByAssignee(user);

    }

    public void save(Task task) {
        taskRepo.save(task);
    }
}
