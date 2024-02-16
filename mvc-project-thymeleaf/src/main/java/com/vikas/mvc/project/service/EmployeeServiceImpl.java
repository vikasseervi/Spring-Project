package com.vikas.mvc.project.service;

import com.vikas.mvc.project.dao.EmployeeRepository;
import com.vikas.mvc.project.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeByUsername(String username) {
        return employeeRepository.findById(username);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeByUsername(String username) {
        employeeRepository.deleteById(username);
    }
}
