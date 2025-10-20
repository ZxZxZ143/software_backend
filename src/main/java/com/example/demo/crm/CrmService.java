package com.example.demo.crm;

import com.example.demo.crm.dto.CourseDto;
import com.example.demo.crm.dto.OperatorDto;
import com.example.demo.crm.dto.OperatorsHandleDto;
import com.example.demo.crm.dto.RequestDto;
import com.example.demo.db.models.CoursesModel;
import com.example.demo.db.models.OperatorsModel;
import com.example.demo.db.models.RequestModel;
import com.example.demo.db.repositories.CoursesRepository;
import com.example.demo.db.repositories.OperatorsRepository;
import com.example.demo.db.repositories.RequestRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
public class CrmService {
    private RequestRepository requestRepository;
    private CoursesRepository coursesRepository;
    private OperatorsRepository operatorsRepository;

    @Autowired
    public CrmService(RequestRepository requestRepository,  CoursesRepository coursesRepository, OperatorsRepository operatorsRepository) {
        this.requestRepository = requestRepository;
        this.coursesRepository = coursesRepository;
        this.operatorsRepository = operatorsRepository;
    }

    public List<OperatorsModel> getAllOperators() {
        return operatorsRepository.findAll();
    }

    @Transactional
    public void addOperator(OperatorDto operatorDto) {
        OperatorsModel operatorsModel = new OperatorsModel();

        operatorsModel.setName(operatorDto.getName());
        operatorsModel.setSurname(operatorDto.getSurname());
        operatorsModel.setDepartment(operatorDto.getDepartment());

        operatorsRepository.save(operatorsModel);
    }

    @Transactional
    public RequestModel addRequest(RequestDto requestDto) {
        RequestModel requestModel = new RequestModel();

        requestModel.setComment(requestDto.getComment());
        requestModel.setPhone(requestDto.getPhone());
        requestModel.setUserName(requestDto.getUsername());
        requestModel.setCourses(this.getCourseById(requestDto.getCourseId()));

        return requestRepository.save(requestModel);
    }

    @Transactional
    public void addCourses(CourseDto courseDto) {
        CoursesModel coursesModel = new CoursesModel();

        coursesModel.setName(courseDto.getName());
        coursesModel.setDescription(courseDto.getDescription());
        coursesModel.setPrice(courseDto.getPrice());

        coursesRepository.save(coursesModel);
    }

    public List<CoursesModel> getCourses() {
        return coursesRepository.findAll();
    }

    public List<RequestModel> gelAllRequests() {
        return requestRepository.findAll();
    }

    public RequestModel getRequestById(long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public CoursesModel getCourseById(long id) {
        return coursesRepository.findById(id).orElse(null);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    @Transactional
    public void handleRequest(Long id, OperatorsHandleDto operatorsHandleDto) {
        Optional<RequestModel> requestModelOp = requestRepository.findById(id);
        RequestModel requestModel;

        if (requestModelOp.isPresent()) {
            requestModel = requestModelOp.get();
        } else {
            return;
        }

        requestModel.setOperators(operatorsRepository.findAllById(operatorsHandleDto.getIds()));
        requestModel.setHandled(true);

        requestRepository.save(requestModel);
    }

    @Transactional
    public void deleteOperatorFromRequest(Long id, Long operatorId) {
        RequestModel requestModel = requestRepository.findById(id).orElse(null);

        if (requestModel == null) return;

        List<OperatorsModel> operators = requestModel.getOperators();

        System.out.println(operators.get(0).getId());
        System.out.println(operators.get(0));

        requestModel.setOperators(operators);

        requestRepository.save(requestModel);
    }

}
