package com.lilas.taskmanager.controller;

import com.lilas.taskmanager.domain.Task;
import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.repo.TaskRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class TaskController {

    private final TaskRepo taskRepo;

    public TaskController(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

   @GetMapping("/main")
    public String main(String name, Map<String, Object> model) {
        model.put("tasks", taskRepo.findAll());
        return "main";
    }

    @PostMapping("/main")
    public String save(@AuthenticationPrincipal User autor,
                       @RequestParam String taskName, @RequestParam(value="taskCreateDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date taskCreateDate,
                       @RequestParam(value="taskUpdateDate") @DateTimeFormat(pattern="yyyy-MM-dd")  Date taskUpdateDate, @RequestParam String taskDescription,
                       Model model) {
        taskRepo.save(new Task(taskName,taskCreateDate,taskUpdateDate,taskDescription,autor));
        model.addAttribute("tasks", taskRepo.findAll());
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {

        if (filter != null && !filter.isEmpty()) {
            model.addAttribute("tasks", taskRepo.findAllByTaskName(filter));
        } else {
            model.addAttribute("tasks", taskRepo.findAll());
        }
        return "main";
    }
}
