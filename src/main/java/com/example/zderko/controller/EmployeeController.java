package com.example.zderko.controller;

import com.example.zderko.PageWrapper;
import com.example.zderko.model.Employee;
import com.example.zderko.service.DepartmentService;
import com.example.zderko.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;
    //    private DepartmentService departmentService;

    public int page_number;
    private int page_size;


    @GetMapping("/employee")
    public String index(Model model, Pageable pageable, HttpSession session) {
        long timeMillisme = System.nanoTime();
        Page<Employee> getEmployeePage =employeeService.findAllEmp(pageable);
        PageWrapper<Employee> page = new PageWrapper<>(getEmployeePage, "/employee");
        model.addAttribute("page", page);
        model.addAttribute("employees", page.getContent());

        page_size = page.getContent().size();
        page_number = page.getNumber()-1;

        session.setAttribute("page_number", page_number);
        session.setAttribute("page_size", page_size);
        System.out.println("При загрузці списку " + (System.nanoTime()-timeMillisme)/1000000);
        return "list";
    }

    @GetMapping("/employee/create")
    public String create(Model model) {
        long timeMillisme = System.nanoTime();
        model.addAttribute("employee", new Employee());
        System.out.println("При створенні нового користувача " + (System.nanoTime()-timeMillisme)/1000000);
        return "form";
    }

    @GetMapping("/employee/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        long timeMillisme = System.nanoTime();

    //        model.addAttribute("department", departmentService.findAll());
        model.addAttribute("employee", employeeService.findOne(id));
        System.out.println("При виборі на апдейт " + (System.nanoTime()-timeMillisme)/1000000);
        return "form";

    }

    @PostMapping("/employee/save")
    public String save(@Valid Employee employee, BindingResult result, RedirectAttributes redirect) {
        long timeMillisme = System.nanoTime();
        if (result.hasErrors()) {
            return "form";
        }
        employeeService.save(employee);
        redirect.addFlashAttribute("success", "Saved employee successfully!");
        System.out.println("При апдейті користучава " + (System.nanoTime()-timeMillisme)/1000000);

        return "redirect:/employee?page=" + page_number;
    }

    @GetMapping("/employee/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        long timeMillisme = System.nanoTime();
        employeeService.delete(id);
        redirect.addFlashAttribute("success", "Deleted employee successfully!");
        System.out.println("При видаленні користучава " + (System.nanoTime()-timeMillisme)/1000000);
        if (page_size == 1){
            return "redirect:/employee?page=" + (page_number -1);
        }else {
            return "redirect:/employee?page=" + page_number;
        }
    }
    @GetMapping("/employee/search")
    public String search(@RequestParam("s") String s, Model model, Pageable pageable ) {
        if (s.equals("")) {
            return "redirect:/employee";
        }
        Page<Employee> getEmployeePage = employeeService.search(s, pageable);
        PageWrapper<Employee> page = new PageWrapper<>(getEmployeePage, "/employee/search?s=" + s );
        model.addAttribute("page", page);
        model.addAttribute("employees", page.getContent());

        return "list";
    }
}
