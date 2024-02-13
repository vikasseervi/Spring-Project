//package com.vikas.mvc.project.dao;
//
//import com.vikas.mvc.project.entity.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//import java.util.List;
//
//@RepositoryRestResource(path = "employees")
//public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//    // That's it... no need to write ani code
//
//    //findAllBy_OrderByFirstName_Asc JPA will parse the method name
//    public List<Employee> findAllByOrderByFirstNameAsc();
//}
