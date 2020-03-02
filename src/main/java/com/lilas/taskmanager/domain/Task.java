package com.lilas.taskmanager.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String taskName;
    private String taskCreateDate;
    private String taskUpdateDate;
    private String taskDescription;
    @ElementCollection(targetClass = TaskStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "task_status", joinColumns = @JoinColumn(name = "task_id"))
    @Enumerated(EnumType.STRING)
    private Set<TaskStatus> taskStatuses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User assignee;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    private TaskStatus taskStatus;

    public Task() {
    }

    public Task(String taskName, String taskCreateDate, String taskUpdateDate, String taskDescription, User assignee, User creator) {
        this.taskName = taskName;
        this.taskCreateDate = taskCreateDate;
        this.taskUpdateDate = taskUpdateDate;
        this.taskDescription = taskDescription;
        this.assignee = assignee;
        this.taskStatus = TaskStatus.NEW_TASK;
        this.creator = creator;
    }

    public Set<TaskStatus> getTaskStatusForEmployee() {
        Set<TaskStatus> taskStatuses = new HashSet<>();
        taskStatuses.addAll(Arrays.asList(TaskStatus.values()));
        taskStatuses.remove(TaskStatus.REOPEN);
        taskStatuses.remove(TaskStatus.DONE);
        return taskStatuses;
    }

    public String getAssigneeName() {
        return assignee.getUsername();
    }

    public String getCreatorName() {
        return creator.getUsername();
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

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
