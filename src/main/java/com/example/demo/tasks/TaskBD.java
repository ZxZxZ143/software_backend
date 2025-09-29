package com.example.demo.tasks;

import com.example.demo.Items.ItemsManagement;

import java.util.ArrayList;
import java.util.List;

public class TaskBD {
    private static TaskBD instance;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public static TaskBD getInstance() {
        if (instance == null) {
            synchronized (ItemsManagement.class) {
                if (instance == null) {
                    instance = new TaskBD();
                }
            }
        }

        return instance;
    }


    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void editTask(long id, String name, String description, String deadline, boolean isCompleted) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setName(name);
                task.setDescription(description);
                task.setDeadlineDate(deadline);
                task.setIsCompleted(isCompleted);

                break;
            }
        }
    }

    public Task getTask(long id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    public void deleteTask(long id) {
        tasks.removeIf(task -> task.getId() == id);
    }

}
