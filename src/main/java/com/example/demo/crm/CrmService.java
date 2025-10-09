package com.example.demo.crm;

import com.example.demo.crm.dto.RequestDto;
import com.example.demo.db.models.RequestModel;
import com.example.demo.db.repositories.RequestRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class CrmService {
    private RequestRepository requestRepository;

    @Autowired
    public CrmService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Transactional
    public RequestModel add(RequestDto requestDto) {
        RequestModel requestModel = new RequestModel();

        requestModel.setComment(requestDto.getComment());
        requestModel.setPhone(requestDto.getPhone());
        requestModel.setCourseName(requestDto.getCourseName());
        requestModel.setUserName(requestDto.getUsername());

        return requestRepository.save(requestModel);
    }

    public List<RequestModel> gelAll() {
        return requestRepository.findAll();
    }

    public RequestModel getById(long id) {
        return requestRepository.findById(id);
    }

    public void delete(Long id) {
        requestRepository.deleteById(id);
    }

    @Transactional
    public void handleRequest(Long id) {
        Optional<RequestModel> requestModelOp = requestRepository.findById(id);
        RequestModel requestModel;

        if (requestModelOp.isPresent()) {
            requestModel = requestModelOp.get();
        } else {
            return;
        }

        requestModel.setHandled(true);

        requestRepository.save(requestModel);
    }

}
