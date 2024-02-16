package com.vikas.mvc.project.service;

import com.vikas.mvc.project.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllByUsername(String username);

    void deleteAllRoleByUsername(String username);

    void saveRole(Role role);
}
