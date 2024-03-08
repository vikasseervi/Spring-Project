package com.vikas.mvc.project.dao;

import com.vikas.mvc.project.entity.Employee;
import com.vikas.mvc.project.entity.Roles;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

//    Sort sortByFirstNameAsc = Sort.by("firstName").ascending();
//    Sort sortByLastNameDesc = Sort.by("lastName").descending();

    Sort sortByFirstName  = Sort.by("firstName").ascending();
    Sort sortByLastName  = Sort.by("lastName").descending();

    List<Employee> findAllByRole(Roles role);

    List<Employee> findAllByOrderByFirstNameAsc();
    List<Employee> findAllByOrderByFirstNameDesc();

    public List<Employee> findAllByOrderByLastNameAsc();
    public List<Employee> findAllByOrderByLastNameDesc();

    public List<Employee> findAllByOrderByEmailAsc();
    public List<Employee> findAllByOrderByEmailDesc();

    public List<Employee> findAllByOrderByRoleAsc();
    public List<Employee> findAllByOrderByRoleDesc();

}
