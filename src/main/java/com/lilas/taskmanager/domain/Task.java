package com.lilas.taskmanager.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String taskName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskCreateDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date taskUpdateDate;
    private String taskDescription;
    @ElementCollection(targetClass = TaskStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "task_status",joinColumns = @JoinColumn(name = "task_id"))
    @Enumerated(EnumType.STRING)
    private Set<TaskStatus> taskStatuses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User autor;

    public Task() {
    }
    public Task(String taskName, Date taskCreateDate, Date taskUpdateDate, String taskDescription , User autor) {
        this.taskName = taskName;
        this.taskCreateDate = taskCreateDate;
        this.taskUpdateDate = taskUpdateDate;
        this.taskDescription = taskDescription;
        this.autor = autor;
    }

    public String getAutorName(){
        return autor.getUsername();
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

    public Date getTaskCreateDate() {
        return taskCreateDate;
    }

    public void setTaskCreateDate(Date taskCreateDate) {
        this.taskCreateDate = taskCreateDate;
    }

    public Date getTaskUpdateDate() {
        return taskUpdateDate;
    }

    public void setTaskUpdateDate(Date taskUpdateDate) {
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

    public void setTaskStatuses(Set<TaskStatus> taskStatuses) {
        this.taskStatuses = taskStatuses;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }
}
