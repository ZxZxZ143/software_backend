package com.example.demo.tasks;

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

    public void editTask(long id, String name, String description, String deadline, String isCompleted) {
        boolean isCompletedBol = isCompleted.equals("on");

        taskBD.editTask(id, name, description, deadline, isCompletedBol);
    }

    public Task getTask(long id) {
        return taskBD.getTask(id);
    }

    public void deleteTask(long id) {
        taskBD.deleteTask(id);
    }
}
