package com.example.demo.studentSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/add")
    public String addPage() {
        return "addStudent";
    }

    @GetMapping("")
    public String studentPage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());

        return "students";
    }

    @GetMapping("/search")
    public String searchStudent(Model model, @RequestParam("query") String search) {
        model.addAttribute("students", studentService.getStudentsByName(search));

        return "students";
    }

    @PostMapping("/add")
    public String add(String name, String surname, String grade) {
        studentService.addStudent(name, surname, Integer.parseInt(grade));
        System.out.println(name);
        return "redirect:/student";
    }
}
