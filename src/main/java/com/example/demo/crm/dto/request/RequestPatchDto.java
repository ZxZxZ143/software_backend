package com.example.demo.crm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestPatchDto {
    private String comment;
    private String phone;
    private String username;
    private boolean handled;
}
