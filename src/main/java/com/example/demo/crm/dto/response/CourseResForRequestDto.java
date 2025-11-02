package com.example.demo.crm.dto.response;

import com.example.demo.crm.db.models.CoursesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseResForRequestDto {
    private Long id;
    private String courseName;

    public CourseResForRequestDto(CoursesModel course) {
        this.id = course.getId();
        this.courseName = course.getName();
    }
}
