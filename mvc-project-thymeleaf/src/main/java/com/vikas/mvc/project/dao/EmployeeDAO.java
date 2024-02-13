package com.vikas.mvc.project.dao;
import com.vikas.mvc.project.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(String user_id);
    void save(Employee employee);
    void deleteById(String user_id);
}