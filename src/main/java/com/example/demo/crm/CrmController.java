package com.example.demo.crm;

import com.example.demo.crm.dto.CourseDto;
import com.example.demo.crm.dto.OperatorDto;
import com.example.demo.crm.dto.OperatorsHandleDto;
import com.example.demo.crm.dto.RequestDto;
import com.example.demo.db.models.RequestModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crm")
public class CrmController {
    private final CrmService crmService;

    @Autowired
    public CrmController(CrmService crmService) {
        this.crmService = crmService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("requestList", crmService.gelAllRequests());

        return "crmMain";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("request", new RequestDto());
        model.addAttribute("courses", crmService.getCourses());

        return "crmAdd";
    }

    @GetMapping("/add/course")
    public String addCourse(Model model) {
        model.addAttribute("course", new CourseDto());

        return "crmAddCourse";
    }

    @PostMapping("/add/course")
    public String addCourse(@Valid @ModelAttribute("course") CourseDto course) {
        crmService.addCourses(course);

        return "redirect:/crm";
    }

    @GetMapping("/request/{id}")
    public String request(Model model, @PathVariable Long id) {
        RequestModel req = crmService.getRequestById(id);

        model.addAttribute("request", req);
        model.addAttribute("operators", crmService.getAllOperators());
        model.addAttribute("operatorsHandle", new OperatorsHandleDto());

        return "crmRequest";
    }

    @GetMapping("/add/operator")
    public String addOperator(Model model) {
        model.addAttribute("operator", new OperatorDto());

        return "crmAddOperator";
    }

    @PostMapping("/add/operator")
    public String addOperator(@Valid @ModelAttribute("operator") OperatorDto operator) {
        crmService.addOperator(operator);

        return "redirect:/crm";
    }

    @PostMapping("/{id}/delete/operator")
    public String addOperator(@PathVariable Long id, Long operatorId) {
        crmService.deleteOperatorFromRequest(id, operatorId);

        return "redirect:/crm/request/" + id;
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute RequestDto requestDto) {
        crmService.addRequest(requestDto);

        return "redirect:/crm";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        crmService.deleteRequest(id);
        return "redirect:/crm";
    }

    @PostMapping("/handle/{id}")
    public String handle(@PathVariable Long id, @ModelAttribute OperatorsHandleDto operatorsHandleDto) {
        crmService.handleRequest(id, operatorsHandleDto);

        return "redirect:/crm/request/" + id;
    }
}
