package com.vikas.mvc.project.controller;

import com.vikas.mvc.project.entity.*;
import com.vikas.mvc.project.service.EmployeeService;
import com.vikas.mvc.project.service.MemberService;
import com.vikas.mvc.project.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final MemberService memberService;
    private final RoleService roleService;

    public EmployeeController(EmployeeService employeeService, MemberService memberService, RoleService roleService) {
        this.employeeService = employeeService;
        this.memberService = memberService;
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model, @RequestParam(name = "sort", defaultValue = "firstName") String sortField,
                                @RequestParam(name = "order", defaultValue = "asc") String sortOrder) {
        List<Employee> employees;
        if (sortField.equals("lastName")) {
            if (sortOrder.equalsIgnoreCase("asc")) employees = employeeService.findAllByOrderByLastNameAsc();
            else employees = employeeService.findAllByOrderByLastNameDesc();
        }
        else if (sortField.equals("email")) {
            if (sortOrder.equals("asc")) employees = employeeService.findAllByOrderByEmailAsc();
            else employees = employeeService.findAllByOrderByEmailDesc();
        }
        else if (sortField.equals("role")) {
            if (sortOrder.equals("asc")) employees = employeeService.findAllByOrderByRoleAsc();
            else employees = employeeService.findAllByOrderByRoleDesc();
        }
        else {
            if (sortOrder.equals("asc")) employees = employeeService.findAllByOrderByFirstNameAsc();
            else employees = employeeService.findAllByOrderByFirstNameDesc();
        }
        model.addAttribute("employees", employees);
        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployees(@ModelAttribute("employee") Employee employee, @RequestParam("oldUsername") String oldUsername) {
        System.out.println(employee + " " + oldUsername);
        if (!oldUsername.isEmpty()) {
            if (!employee.getUsername().equals(oldUsername)) {
                Member member = memberService.findByUsername(oldUsername);
                member.setUsername(employee.getUsername());
                Role role = new Role(new RoleId(employee.getUsername(), employee.getRole()));
                employeeService.deleteEmployeeByUsername(oldUsername);
                roleService.deleteAllRoleByUsername(oldUsername);
                memberService.deleteMemberByUsername(oldUsername);
                memberService.saveMember(member);
                employeeService.saveEmployee(employee);
                roleService.saveRole(role);
            }
            else {
                employeeService.saveEmployee(employee);
            }
        }
        else {
            if (memberService.findByUsername(employee.getUsername()) != null) {

            }
            memberService.saveMember(new Member(employee.getUsername()));
            employeeService.saveEmployee(employee);
        }
        return "redirect:/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeUsername") String username, Model model) {
        // get the employee form the service/DB
        Optional<Employee> employee = employeeService.findEmployeeByUsername(username);

        // set employee in the model to pre-populate the form
        model.addAttribute("employee", employee);
        model.addAttribute("oldUsername", employee.get().getUsername());

        // send over to our form
        return "employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeUsername") String username) {
        employeeService.deleteEmployeeByUsername(username);
        roleService.deleteAllRoleByUsername(username);
        memberService.deleteMemberByUsername(username);
        return "redirect:/list";
    }
}
