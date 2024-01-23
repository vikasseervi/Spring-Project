package com.vikas.mvc.project.controller;

import com.vikas.mvc.project.entity.Employee;
import com.vikas.mvc.project.service.EmployeeService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model){
        // get employees from db
        List<Employee> employees = employeeService.findAll();
        // add to the model
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployees(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model){
        // get the employee form the service/DB
        Employee employee = employeeService.findById(id);

        // set employee in the model to pre-populate the form
        model.addAttribute("employee", employee);

        // send over to our form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
