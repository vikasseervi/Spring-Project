package com.vikas.mvc.project.dao;
import com.vikas.mvc.project.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAllEmployee();
    Employee findEmployeeByUsername(String username);
    int findIdByUsername(String username);


    void saveEmployee(Employee employee);


    void deleteEmployeeByUsername(String username);
    void deleteMemberById(int id);
    void deleteRoleByUsername(String username);
}