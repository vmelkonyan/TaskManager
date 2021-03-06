package com.lilas.taskmanager.repo;

import com.lilas.taskmanager.domain.Task;
import com.lilas.taskmanager.domain.TaskStatus;
import com.lilas.taskmanager.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {
    List<Task> findAllByTaskNameContaining(String filter);

    List<Task> findAllByAssignee(User author);

    List<Task> findAllByAssigneeAndTaskNameContaining(User author,String filter);

    List<Task> findAllByAssigneeContainingAndTaskNameContaining(User author,String filter);

    List<Task> findAllByAssigneeContainingAndTaskNameContainingAndTaskStatus(User author, String filter, TaskStatus taskStatus);

}
