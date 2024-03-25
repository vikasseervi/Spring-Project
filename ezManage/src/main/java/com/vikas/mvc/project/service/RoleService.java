package com.vikas.mvc.project.service;

import com.vikas.mvc.project.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

public interface RoleService {

    List<Role> findAllByUsername(String username);

    @Transactional
    void deleteAllRoleByUsername(String username);

    @Transactional
    void saveRole(Role role);
}
