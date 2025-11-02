package com.example.demo.crm.controller;

import com.example.demo.crm.dto.request.OperatorDto;
import com.example.demo.crm.dto.response.OperatorsResDto;
import com.example.demo.crm.dto.response.RequestDetailDto;
import com.example.demo.crm.service.OperatorsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operators")
public class OperatorsController {
    OperatorsService operatorsService;

    @Autowired
    public OperatorsController(OperatorsService operatorsService) {
        this.operatorsService = operatorsService;
    }

    @GetMapping()
    public ResponseEntity<List<OperatorsResDto>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(operatorsService.findAll());
    }

    @PostMapping()
    public ResponseEntity<OperatorsResDto> create(@Valid @RequestBody OperatorDto operatorDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(operatorsService.create(operatorDto));
    }

    @PatchMapping("/{operatorId}/assign/{requestId}")
    public ResponseEntity<RequestDetailDto> assign(@PathVariable("operatorId") Long operatorId, @PathVariable("requestId") Long requestId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(operatorsService.assign(operatorId, requestId));
    }
}
