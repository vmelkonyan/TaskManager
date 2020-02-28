package com.lilas.taskmanager.controller;

import com.lilas.taskmanager.domain.Task;
import com.lilas.taskmanager.domain.TaskStatus;
import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.repo.TaskRepo;
import com.lilas.taskmanager.repo.UserRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Controller
public class TaskController {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public TaskController(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, @AuthenticationPrincipal User user, Model model) {


        checkBeforeShown(filter, user, model);
        model.addAttribute("filter", filter);
        return "main";
    }

    private void checkBeforeShown(@RequestParam(required = false, defaultValue = "") String filter, @AuthenticationPrincipal User user, Model model) {
        if (filter != null && !filter.isEmpty()) {
            if (user.isManager()) {
                model.addAttribute("tasks", taskRepo.findAllByTaskName(filter));
            } else {
                model.addAttribute("tasks", taskRepo.findAllByAuthorAndTaskName(user, filter));
            }
        } else {

            if (user.isManager()) {
                model.addAttribute("tasks", taskRepo.findAll());
            } else {
                model.addAttribute("tasks", taskRepo.findAllByAuthor(user));
            }
        }
    }

    @PostMapping("/main")
    public String save(@AuthenticationPrincipal User user,
                       @RequestParam String
                               taskName, @RequestParam(value = "taskCreateDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date taskCreateDate,
                       @RequestParam(value = "taskUpdateDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date
                               taskUpdateDate, @RequestParam String taskDescription,
                       Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        taskRepo.save(new Task(taskName, dateFormat.format(date), dateFormat.format(date), taskDescription, user));
        if (user.isManager()) {
            model.addAttribute("tasks", taskRepo.findAll());
        } else {
            model.addAttribute("tasks", taskRepo.findAllByAuthor(user));
        }
        ;
        return "main";
    }

    @GetMapping("/editTask/{task}")
    public String editTask(@PathVariable Task task, Model model) {
        model.addAttribute("taskStatuss", TaskStatus.values());
        model.addAttribute("usersList", userRepo.findAll());
        return "taskEdit";
    }

    @PostMapping("/editTask")
    public String saveChanges(@RequestParam() String taskName,
                              @RequestParam() String taskDescription,
                              @RequestParam() Map<String, String> form,
                              @RequestParam("taskId") Task task) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        task.setTaskName(taskName);
        if (form.get("taskStatus") != null) {
            task.setTaskStatus(TaskStatus.valueOf(form.get("taskStatus")));
        }
        task.setTaskDescription(taskDescription);
        task.setTaskUpdateDate(dateFormat.format(date));
        taskRepo.save(task);
        return "redirect:/main";
    }

//    @PostMapping("/filter")
//    public String filter(@RequestParam String filter, Model model) {
//
//        if (filter != null && !filter.isEmpty()) {
//            model.addAttribute("tasks", taskRepo.findAllByTaskName(filter));
//        } else {
//            model.addAttribute("tasks", taskRepo.findAll());
//        }
//        return "redirect:/main";
//    }
}
