package com.vikas.mvc.project.controller;

import com.vikas.mvc.project.entity.Employee;
import com.vikas.mvc.project.entity.Member;
import com.vikas.mvc.project.entity.Role;
import com.vikas.mvc.project.service.EmployeeService;
import com.vikas.mvc.project.service.MemberService;
import com.vikas.mvc.project.service.RoleService;
import com.vikas.mvc.project.service.RoleServiceImpl;
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

    public EmployeeController(EmployeeService employeeService, MemberService memberService, RoleService roleService){
        this.employeeService = employeeService;
        this.memberService = memberService;
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model){
        List<Employee> employees = employeeService.findAllEmployees();
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
    public String saveEmployees(@ModelAttribute("employee") Employee employee, @RequestParam("oldUsername") String oldUsername){
        System.out.println(employee+" "+oldUsername);
        if(!oldUsername.isEmpty() && !employee.getUsername().equals(oldUsername)) {
            Member member = memberService.findByUsername(oldUsername);
            member.setUsername(employee.getUsername());
            memberService.deleteMemberByUsername(oldUsername);
            employeeService.deleteEmployeeByUsername(oldUsername);
            roleService.deleteRoleByUsername(oldUsername);
            memberService.saveMember(member);
            employeeService.saveEmployee(employee);
            roleService.saveRole(new Role(employee.getUsername(), employee.getRole()));
        }
        else {
            memberService.saveMember(new Member(employee.getUsername()));
            employeeService.saveEmployee(employee);
        }
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeUsername") String username, Model model){
        // get the employee form the service/DB
        Optional<Employee> employee = employeeService.findEmployeeByUsername(username);

        // set employee in the model to pre-populate the form
        model.addAttribute("employee", employee);
        model.addAttribute("oldUsername", employee.get().getUsername());

        // send over to our form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeUsername") String username){
        employeeService.deleteEmployeeByUsername(username);
        return "redirect:/employees/list";
    }
}
