package com.example.zderko.controller;

import com.example.zderko.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department")
    public String index(Model model){
        model.addAttribute("department", departmentService.findAll());

        return "form1";
    }
}
