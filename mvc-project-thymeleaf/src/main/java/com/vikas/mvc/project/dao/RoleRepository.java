package com.vikas.mvc.project.dao;

import com.vikas.mvc.project.entity.Role;
import com.vikas.mvc.project.entity.RoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, RoleId> {

    List<Role> findByRoleIdUsername(String username);
    void deleteByRoleIdUsername(String username);


}
