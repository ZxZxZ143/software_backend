package com.example.demo.crm.dto.response;

import com.example.demo.crm.db.models.OperatorsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperatorsResDto {
    private Long id;
    private String name;
    private String surname;
    private String department;

    public OperatorsResDto(OperatorsModel operatorsModel) {
        this.id =  operatorsModel.getId();
        this.name = operatorsModel.getName();
        this.surname = operatorsModel.getSurname();
        this.department = operatorsModel.getDepartment();
    }
}
