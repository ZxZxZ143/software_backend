package com.example.demo.crm.service;

import com.example.demo.crm.db.models.OperatorsModel;
import com.example.demo.crm.db.models.RequestModel;
import com.example.demo.crm.db.repositories.OperatorsRepository;
import com.example.demo.crm.db.repositories.RequestRepository;
import com.example.demo.crm.dto.request.OperatorDto;
import com.example.demo.crm.dto.response.OperatorsResDto;
import com.example.demo.crm.dto.response.RequestDetailDto;
import com.example.demo.crm.dto.response.RequestResDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorsService {
    private OperatorsRepository operatorsRepository;
    private RequestRepository requestRepository;

    @Autowired
    public OperatorsService(OperatorsRepository operatorsRepository, RequestRepository requestRepository) {
        this.operatorsRepository = operatorsRepository;
        this.requestRepository = requestRepository;
    }

    public List<OperatorsResDto> findAll() {
        return operatorsRepository.findAll()
                .stream()
                .map(OperatorsResDto::new)
                .toList();
    }

    @Transactional
    public OperatorsResDto create(OperatorDto operatorsDto) {
        OperatorsModel operatorsModel = new OperatorsModel();

        operatorsModel.setName(operatorsDto.getName());
        operatorsModel.setSurname(operatorsDto.getSurname());
        operatorsModel.setDepartment(operatorsDto.getDepartment());

        return new OperatorsResDto(operatorsRepository.save(operatorsModel));
    }

    @Transactional
    public RequestDetailDto assign(long operatorId, long requestId) {
        RequestModel requestModel = requestRepository
                .findById(requestId)
                .orElseThrow(() -> new RuntimeException("No Such Request"));

        OperatorsModel operatorsModel = operatorsRepository
                .findById(operatorId)
                .orElseThrow(() -> new RuntimeException("No Such Operator"));

        requestModel.getOperators().add(operatorsModel);
        requestRepository.save(requestModel);

        return new RequestDetailDto(requestModel);
    }
}
