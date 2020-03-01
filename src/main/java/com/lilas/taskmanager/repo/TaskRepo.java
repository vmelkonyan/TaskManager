package com.lilas.taskmanager.repo;

import com.lilas.taskmanager.domain.Task;
import com.lilas.taskmanager.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {
    List<Task> findAllByTaskName(String filter);

    List<Task> findAllByAssignee(User author);

    List<Task> findAllByAssigneeAndTaskName(User author,String filter);
}
