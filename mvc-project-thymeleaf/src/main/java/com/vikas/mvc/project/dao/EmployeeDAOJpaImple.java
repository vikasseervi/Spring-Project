package com.vikas.mvc.project.dao;

import com.vikas.mvc.project.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOJpaImple implements EmployeeDAO {
    private EntityManager entityManager;
    @Autowired
    public EmployeeDAOJpaImple(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public Employee findById(String user_id) {
        Employee employee = entityManager.find(Employee.class, user_id);
        return employee;
    }

    // @Transactional will be handled by Service layer instead of DAO layer

    @Override
    @Transactional
    public void save(Employee employee) {
        // merge() will update and create with new id if the object is not found

        entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void deleteById(String user_id) {
        Employee employee = findById(user_id);
        entityManager.remove(employee);
    }

}