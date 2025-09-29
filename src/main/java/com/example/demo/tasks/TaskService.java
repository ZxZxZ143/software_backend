package com.example.demo.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskBD taskBD = TaskBD.getInstance();

    public List<Task> getTasks() {
        return taskBD.getTasks();
    }

    public void addTask(String name, String description, String deadline) {
        Task newTask = new Task(taskBD.getSize(), name, description, deadline, false);

        taskBD.addTask(newTask);
    }

    public void editTask(long id, Task task) {}
}
