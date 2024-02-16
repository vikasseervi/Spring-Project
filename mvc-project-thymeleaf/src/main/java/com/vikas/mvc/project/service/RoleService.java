package com.vikas.mvc.project.service;

import com.vikas.mvc.project.entity.Role;
import org.springframework.transaction.annotation.Transactional;

public interface RoleService {

    void saveRole(Role role);

    void deleteRoleByUsername(String username);
}
