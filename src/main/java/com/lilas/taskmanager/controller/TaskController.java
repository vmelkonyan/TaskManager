package com.lilas.taskmanager.controller;

import com.lilas.taskmanager.constatns.KeyConstants;
import com.lilas.taskmanager.domain.Task;
import com.lilas.taskmanager.domain.TaskStatus;
import com.lilas.taskmanager.domain.User;
import com.lilas.taskmanager.exception.AppException;
import com.lilas.taskmanager.serice.TaskService;
import com.lilas.taskmanager.serice.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class TaskController {
    private static final Logger LOGGER= LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping(KeyConstants.MAIN_KEY)
    public String main(@RequestParam(required = false, defaultValue = "") String filter, @AuthenticationPrincipal User user, Model model) {
        checkBeforeShown(filter, user, model);
        model.addAttribute("filter", filter);
        return KeyConstants.MAIN_VIEW_KEY;
    }

    private void checkBeforeShown(@RequestParam(required = false, defaultValue = "") String filter, @AuthenticationPrincipal User user, Model model) {
        if (filter != null && !filter.isEmpty()) {
            if (user.isManager()) {
                model.addAttribute("tasks", taskService.findAllByTaskName(filter));
            } else {
                model.addAttribute("tasks", taskService.findAllByAssigneeAndTaskName(user, filter));
            }
        } else {
            if (user.isManager()) {
                model.addAttribute("tasks", taskService.findAll());
            } else {
                model.addAttribute("tasks", taskService.findAllByAssignee(user));
            }
        }
    }

    @PostMapping(KeyConstants.MAIN_KEY)
    public String createTask(@AuthenticationPrincipal User user,
                             @RequestParam String taskName, @RequestParam String taskDescription,
                             Model model) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        taskService.save(new Task(taskName, dateFormat.format(date), dateFormat.format(date), taskDescription, user, user));
        if (user.isManager()) {
            model.addAttribute("usersList", userService.findAll());
            model.addAttribute("tasks", taskService.findAll());
        } else {
            model.addAttribute("tasks", taskService.findAllByAssignee(user));
        }
        LOGGER.info("Task correct add");
        return KeyConstants.MAIN_VIEW_KEY;
    }

    @GetMapping(KeyConstants.EDIT_TASK_KEY + "/{task}")
    public String editTask(@AuthenticationPrincipal User user, @PathVariable Task task, Model model) throws AppException {

        if (task != null) {
            if (user.isManager()) {
                model.addAttribute("taskStatuss", TaskStatus.values());
            } else {
                model.addAttribute("taskStatuss", task.getTaskStatusForEmployee());
            }
            model.addAttribute("usersList", userService.findAll());
        } else {
            throw new AppException();

        }
        LOGGER.info("Task edit");
        return KeyConstants.TASK_EDIT_VIEW;
    }

    @PostMapping(KeyConstants.EDIT_TASK_KEY)
    public String updateTaskChanges(@RequestParam() String taskName,
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
        if (form.get("taskOwner") != null) {
            task.setAssignee(userService.findByUsername(form.get("taskOwner")));
        }
        task.setTaskDescription(taskDescription);
        task.setTaskUpdateDate(dateFormat.format(date));
        taskService.save(task);
        LOGGER.info("Task correct update");
        return KeyConstants.REDIRECT_KEY + KeyConstants.MAIN_KEY;
    }

    @ExceptionHandler(AppException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "Task not found");
        mav.setViewName("error");
        return mav;
    }
}
