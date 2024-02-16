package com.vikas.mvc.project.service;

import com.vikas.mvc.project.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAllEmployees();

    Optional<Employee> findEmployeeByUsername(String username);

    void saveEmployee(Employee employee);

    void deleteEmployeeByUsername(String username);

}
