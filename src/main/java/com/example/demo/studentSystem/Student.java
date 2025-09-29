package com.example.demo.studentSystem;

import lombok.Data;

@Data
public class Student {
    private long id;
    private String name;
    private String surname;
    private int grade;
    private String mark;

    public Student(long id, String name, String surname, int grade) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.grade = grade;
        this.mark = calculateMark(grade);
    }

    private String calculateMark(int grade) {
        if (grade >= 0 && grade < 50) {
            return "F";
        }
        if (grade >= 50 && grade < 60) {
            return "D";
        }
        if (grade >= 60 && grade < 74) {
            return "C";
        }
        if (grade >= 74 && grade < 90) {
            return "B";
        }
        return "A";
    }
}
