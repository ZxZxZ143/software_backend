package com.example.demo.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    private long id;
    private String name;
    private String description;
    private String deadlineDate;
    private Boolean isCompleted;
}
