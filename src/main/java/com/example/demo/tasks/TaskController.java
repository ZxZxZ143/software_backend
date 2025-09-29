package com.example.demo.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getTasks());

        return "tasks";
    }

    @PostMapping("/add")
    public String addTask(String name, String description, String deadline) {
        taskService.addTask(name, description, deadline);

        return "redirect:/tasks";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@PathVariable long id, String name, String description, String deadline, String isCompleted) {
        taskService.editTask(id, name, description, deadline, isCompleted);

        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);

        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable long id, Model model) {
        Task task = taskService.getTask(id);
        if (task == null) {
            return "redirect:/tasks";
        }

        model.addAttribute("task", task);

        return "editTask";
    }
}
