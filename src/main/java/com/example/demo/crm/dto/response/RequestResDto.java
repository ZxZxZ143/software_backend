package com.example.demo.crm.dto.response;

import com.example.demo.crm.db.models.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestResDto {
    private Long id;
    private String username;
    private CourseResForRequestDto course;
    private String phone;
    private boolean handled;
    private String comment;

    public RequestResDto(RequestModel requestModel) {
        this.id = requestModel.getId();
        this.username = requestModel.getUserName();
        this.course = new CourseResForRequestDto(requestModel.getCourses());
        this.phone = requestModel.getPhone();
        this.handled = requestModel.getHandled();
        this.comment = requestModel.getComment();
    }
}
