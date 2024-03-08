package com.vikas.mvc.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @EmbeddedId
    private RoleId roleId;

    public Role(RoleId roleId) {
        this.roleId = roleId;
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public void setRoleId(RoleId roleId) {
        this.roleId = roleId;
    }
}
