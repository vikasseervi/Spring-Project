package com.vikas.mvc.project.service;

import com.vikas.mvc.project.dao.RoleRepository;
import com.vikas.mvc.project.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllByUsername(String username) {
        return roleRepository.findByRoleIdUsername(username);
    }

    @Override
    public void deleteAllRoleByUsername(String username) {
        roleRepository.deleteByRoleIdUsername(username);
    }


    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
