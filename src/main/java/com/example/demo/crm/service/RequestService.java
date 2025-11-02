package com.example.demo.crm.service;

import com.example.demo.crm.db.models.CoursesModel;
import com.example.demo.crm.db.models.RequestModel;
import com.example.demo.crm.db.repositories.RequestRepository;
import com.example.demo.crm.dto.request.RequestDto;
import com.example.demo.crm.dto.request.RequestPatchDto;
import com.example.demo.crm.dto.response.RequestDetailDto;
import com.example.demo.crm.dto.response.RequestResDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class RequestService {
    RequestRepository requestRepository;
    CourseService courseService;

    @Autowired
    public RequestService(RequestRepository requestRepository, CourseService courseService) {
        this.requestRepository = requestRepository;
        this.courseService = courseService;
    }

    public List<RequestResDto> getAll() {
        return requestRepository
                .findAll()
                .stream()
                .map(RequestResDto::new)
                .toList();
    }

    public RequestDetailDto getById(Long id) {
        return requestRepository.findById(id)
                .map(RequestDetailDto::new)
                .orElse(null);
    }

    @Transactional
    public RequestResDto create(RequestDto requestDto) {
        RequestModel requestModel = new RequestModel();
        CoursesModel coursesModel = Optional.ofNullable(courseService.getById(requestDto.getCourseId()))
                .orElseThrow(() -> new RuntimeException("No courses found"));

        requestModel.setComment(requestDto.getComment());
        requestModel.setPhone(requestDto.getPhone());
        requestModel.setUserName(requestDto.getUsername());
        requestModel.setCourses(coursesModel);

        return new RequestResDto(requestRepository.save(requestModel));
    }

    @Transactional
    public RequestResDto update(long id, RequestPatchDto requestPatchDto) {
        RequestModel requestModel = requestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not found"));

        Map<Supplier<Object>, Runnable> updates = Map.of(
                requestPatchDto::getComment, () -> requestModel.setComment(requestPatchDto.getComment()),
                requestPatchDto::getPhone, () -> requestModel.setPhone(requestPatchDto.getPhone()),
                requestPatchDto::getUsername, () -> requestModel.setUserName(requestPatchDto.getUsername()),
                requestPatchDto::isHandled, () -> requestModel.setHandled(requestPatchDto.isHandled())
        );

        updates.forEach((supplier, runnable) -> {
            if (supplier.get() != null) {
                runnable.run();
            }
        });

        requestRepository.save(requestModel);

        return new RequestResDto(requestModel);
    }

    @Transactional
    public void delete(long id) {
        requestRepository.deleteById(id);
    }
}
