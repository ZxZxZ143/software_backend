package com.example.demo.crm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestDto {

    @Size(min=1, max=50)
    @NotBlank(message = "username can not be blank")
    private String username;

    @Size(min=1, max=150)
    private String comment;

    @NotBlank
    private String courseName;

    @NotBlank(message = "phone can not be blank")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Invalid phone number")
    private String phone;
}
