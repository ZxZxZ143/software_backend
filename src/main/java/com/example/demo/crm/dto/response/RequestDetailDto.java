package com.example.demo.crm.dto.response;

import com.example.demo.crm.db.models.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDetailDto extends RequestResDto {
    private List<OperatorsResDto> operators;

    public RequestDetailDto(RequestModel requestModel) {
        super(requestModel);
        this.operators = requestModel.getOperators()
                .stream()
                .map(OperatorsResDto::new)
                .toList();
    }
}
