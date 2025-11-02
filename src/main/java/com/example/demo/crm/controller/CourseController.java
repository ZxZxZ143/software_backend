package com.example.demo.crm.controller;

import com.example.demo.crm.dto.request.CourseDto;
import com.example.demo.crm.dto.response.CourseResDto;
import com.example.demo.crm.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseResDto>> getAllCourses(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseService.getAll());
    }

    @PostMapping()
    public ResponseEntity<CourseResDto> create(@Valid @RequestBody CourseDto courseDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseService.create(courseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        courseService.delete(id);

        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
