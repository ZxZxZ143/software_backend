package com.example.demo.crm;

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
        model.addAttribute("requestList", crmService.gelAll());

        return "crmMain";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("request", new RequestDto());

        return "crmAdd";
    }

    @GetMapping("/request/{id}")
    public String request(Model model, @PathVariable Long id) {
        RequestModel req = crmService.getById(id);

        model.addAttribute("request", req);

        return "crmRequest";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute RequestDto requestDto, Model model) {
        crmService.add(requestDto);

        return "redirect:/crm";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        crmService.delete(id);
        return "redirect:/crm";
    }

    @PostMapping("/handle/{id}")
    public String handle(@PathVariable Long id) {
        crmService.handleRequest(id);

        return "redirect:/crm";
    }
}
