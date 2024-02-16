//package com.vikas.mvc.project.dao;
//
//import com.vikas.mvc.project.entity.Employee;
//import com.vikas.mvc.project.entity.Member;
//import com.vikas.mvc.project.entity.Role;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.NoResultException;
//import jakarta.persistence.Query;
//import jakarta.persistence.TypedQuery;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//
//@Repository
//public class EmployeeDAOJpaImple implements EmployeeDAO {
//    private final EntityManager entityManager;
//    @Autowired
//    public EmployeeDAOJpaImple(EntityManager entityManager){
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    public List<Employee> findAllEmployee() {
//        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
//        List<Employee> employees = query.getResultList();
//        return employees;
//    }
//
//    @Override
//    public Employee findEmployeeByUsername(String username) {
//        Employee employee = entityManager.find(Employee.class, username);
//        return employee;
//    }
//
//    @Override
//    public int findIdByUsername(String username) {
//        String query = "SELECT id FROM member WHERE username = :username";
//        Query q = entityManager.createNativeQuery(query);
//        q.setParameter("username", username);
//
//        try {
//            return Integer.parseInt(q.getSingleResult().toString());
//        } catch (NoResultException e) {
//            return -1;
//        }
//    }
//
//    @Override
//    @Transactional
//    public void saveEmployee(@NotNull Employee employee) {
////        merge() will update and create with new id if the object is not found
////        System.out.println(employee.toString());
////        entityManager.createQuery("UPDATE Member m SET m.username = :newUsername WHERE m.id = :id")
////                .setParameter("newUsername", employee.getUsername())
////                .setParameter("id", employee.getId())
////                .executeUpdate();
//        // Member member = entityManager.find(Member.class, employee.getId());
//        int id = findIdByUsername(employee.getUsername());
//        if(id != -1) { // should update instead of deleting
//            deleteEmployeeByUsername(employee.getUsername());
//            deleteRoleByUsername(employee.getUsername());
//            deleteMemberById(id);
//        }
//        Member member = new Member(employee.getUsername());
//        entityManager.merge(member);
//        Role role = new Role(employee.getUsername(), employee.getRole());
//        entityManager.merge(role);
//
//        entityManager.merge(employee);
//    }
//
//    @Override
//    @Transactional
//    public void deleteMemberById(int id) {
//        String query = "DELETE FROM member WHERE id = ?";
//        Query q = entityManager.createNativeQuery(query);
//        q.setParameter(1, id);
//        q.executeUpdate();
//    }
//
//    @Override
//    @Transactional
//    public void deleteEmployeeByUsername(String username) {
//        Employee employee = entityManager.find(Employee.class, username);
//        entityManager.remove(employee);
//    }
//
//    @Override
//    @Transactional
//    public void deleteRoleByUsername(String username) {
//        String query = "DELETE FROM role WHERE username = ?";
//        Query q = entityManager.createNativeQuery(query);
//        q.setParameter(1, username);
//        q.executeUpdate();
//    }
//
//
//}