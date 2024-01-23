package com.vikas.mvc.project.service;

import com.vikas.mvc.project.dao.EmployeeRepository;
import com.vikas.mvc.project.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    // No need of adding @Transactional as JPA handles it
    @Autowired
    public EmployeeServiceImpl (EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;
        if(result.isPresent()){
            employee = result.get();
        }
        else{
            throw new RuntimeException("Did not find employee id - "+id);
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
