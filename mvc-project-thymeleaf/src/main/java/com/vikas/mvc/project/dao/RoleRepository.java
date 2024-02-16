package com.vikas.mvc.project.dao;

import com.vikas.mvc.project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByUsername(String username);

    List<Role> findAllByRole(String role);


}
