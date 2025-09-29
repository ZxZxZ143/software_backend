package com.example.demo.studentSystem;

import com.example.demo.Items.Item;
import com.example.demo.Items.ItemsManagement;

import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    private static StudentDB instance;
    private final ArrayList<Student> students =  new ArrayList<>();

    public static StudentDB getInstance() {
        if (instance == null) {
            synchronized (ItemsManagement.class) {
                if (instance == null) {
                    instance = new StudentDB();
                }
            }
        }

        return instance;
    }


    public void addSStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public int getSize() {
        return students.size();
    }
}
