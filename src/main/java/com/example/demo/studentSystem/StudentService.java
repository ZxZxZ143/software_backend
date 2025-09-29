package com.example.demo.studentSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private StudentDB studentDB;

    public StudentService() {
        this.studentDB = StudentDB.getInstance();
    }

    public void addStudent(String name, String surname, int grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException();
        }

        Student newStudent = new Student(studentDB.getSize(), name, surname, grade);

        studentDB.addSStudent(newStudent);
    }

    public List<Student> getAllStudents() {
        return studentDB.getStudents();
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : studentDB.getStudents()) {
            if (student.getName().contains(name)) {
                filteredStudents.add(student);
            }
        }

        return filteredStudents;
    }
}
