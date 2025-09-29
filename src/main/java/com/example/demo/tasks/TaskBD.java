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

}
