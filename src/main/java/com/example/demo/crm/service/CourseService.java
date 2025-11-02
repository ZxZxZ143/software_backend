package com.example.demo.crm.service;

import com.example.demo.crm.db.models.CoursesModel;
import com.example.demo.crm.db.repositories.CoursesRepository;
import com.example.demo.crm.dto.request.CourseDto;
import com.example.demo.crm.dto.response.CourseResDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private CoursesRepository coursesRepository;

    @Autowired
    public CourseService(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    public CoursesModel getById(long id) {
        return coursesRepository.findById(id).orElse(null);
    }

    public List<CourseResDto> getAll() {
        return coursesRepository.findAll()
                .stream()
                .map(CourseResDto::new)
                .toList();
    }

    @Transactional
    public CourseResDto create(CourseDto courseDto) {
        CoursesModel coursesModel = new CoursesModel();

        coursesModel.setName(courseDto.getName());
        coursesModel.setDescription(courseDto.getDescription());
        coursesModel.setPrice(courseDto.getPrice());

        coursesRepository.save(coursesModel);

        return new CourseResDto(coursesModel);
    }

    @Transactional
    public void delete(Long id) {
        coursesRepository.deleteById(id);
    }
}
