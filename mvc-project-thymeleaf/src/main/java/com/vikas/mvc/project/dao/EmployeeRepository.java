package com.vikas.mvc.project.dao;

import com.vikas.mvc.project.entity.Employee;
import com.vikas.mvc.project.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByRole(Roles role);

    public List<Employee> findAllByOrderByLastNameAsc();

}
