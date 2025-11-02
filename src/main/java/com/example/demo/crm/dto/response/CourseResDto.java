package com.example.demo.crm.dto.response;

import com.example.demo.crm.db.models.CoursesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseResDto {
    public long courseId;
    public String courseName;
    public String description;
    public int price;

    public CourseResDto(CoursesModel course) {
        this.courseId = course.getId();
        this.courseName = course.getName();
        this.description = course.getDescription();
        this.price = course.getPrice();
    }
}
