package com.vikas.mvc.project.controller;

import com.vikas.mvc.project.dao.EmployeeDAO;
import com.vikas.mvc.project.entity.Employee;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeDAO employeeDAO;
    public EmployeeController(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("/list")
    public String listEmployees(Model model){
        // get employees from db
        List<Employee> employees = employeeDAO.findAll();
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
        employee.setUser_id(employee.getUser_id());
        employeeDAO.save(employee);
        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") String id, Model model){
        // get the employee form the service/DB
        Employee employee = employeeDAO.findById(id);

        // set employee in the model to pre-populate the form
        model.addAttribute("employee", employee);

        // send over to our form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") String user_id){
        employeeDAO.deleteById(user_id);
        return "redirect:/employees/list";
    }
}
