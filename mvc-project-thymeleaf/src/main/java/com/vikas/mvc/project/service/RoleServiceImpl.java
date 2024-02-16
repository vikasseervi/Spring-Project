package com.vikas.mvc.project.service;

import com.vikas.mvc.project.dao.RoleRepository;
import com.vikas.mvc.project.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void saveRole(Role role){
        roleRepository.save(role);
    }

    @Override
    public void deleteRoleByUsername(String username) {
        roleRepository.deleteById(username);
    }
}
