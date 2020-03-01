package com.lilas.taskmanager.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String taskName;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String taskCreateDate;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String taskUpdateDate;
    private String taskDescription;
    @ElementCollection(targetClass = TaskStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "task_status", joinColumns = @JoinColumn(name = "task_id"))
    @Enumerated(EnumType.STRING)
    private Set<TaskStatus> taskStatuses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private TaskStatus taskStatus;

    public Task() {
    }

    public Task(String taskName, String taskCreateDate, String taskUpdateDate, String taskDescription, User author) {
        this.taskName = taskName;
        this.taskCreateDate = taskCreateDate;
        this.taskUpdateDate = taskUpdateDate;
        this.taskDescription = taskDescription;
        this.author = author;
        this.taskStatus = TaskStatus.NEW_TASK;
    }

    public Set<TaskStatus> getTaskStatusForEmployee() {
        Set<TaskStatus> taskStatuses = new HashSet<>();
        taskStatuses.addAll(Arrays.asList(TaskStatus.values()));
        taskStatuses.remove(TaskStatus.REOPEN);
        taskStatuses.remove(TaskStatus.DONE);
        return taskStatuses;
    }

    public String getAutorName() {
        return author.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCreateDate() {
        return taskCreateDate;
    }

    public void setTaskCreateDate(String taskCreateDate) {
        this.taskCreateDate = taskCreateDate;
    }

    public String getTaskUpdateDate() {
        return taskUpdateDate;
    }

    public void setTaskUpdateDate(String taskUpdateDate) {
        this.taskUpdateDate = taskUpdateDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Set<TaskStatus> getTaskStatuses() {
        return taskStatuses;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTaskStatuses(Set<TaskStatus> taskStatuses) {
        this.taskStatuses = taskStatuses;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
