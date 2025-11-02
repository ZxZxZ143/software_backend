package com.example.demo.crm.controller;

import com.example.demo.crm.db.models.RequestModel;
import com.example.demo.crm.dto.request.RequestDto;
import com.example.demo.crm.dto.request.RequestPatchDto;
import com.example.demo.crm.dto.response.RequestDetailDto;
import com.example.demo.crm.dto.response.RequestResDto;
import com.example.demo.crm.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping()
    public ResponseEntity<List<RequestResDto>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(requestService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<RequestDetailDto> getById(@PathVariable Long id) {
        return Optional.ofNullable(requestService.getById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<RequestResDto> create(@Valid @RequestBody RequestDto requestDto) {
        return Optional.ofNullable(requestService.create(requestDto))
                .map(requestModel -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(requestModel))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RequestResDto> update(@PathVariable Long id, @RequestBody RequestPatchDto requestPatchDto) {
        return Optional.ofNullable(requestService.update(id, requestPatchDto))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        requestService.delete(id);
        return ResponseEntity.ok().build();
    }
}
