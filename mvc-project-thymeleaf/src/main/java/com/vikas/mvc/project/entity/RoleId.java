package com.vikas.mvc.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

public class RoleId implements Serializable {
    @Column(name = "username")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Roles role;

    public RoleId(String username, Roles role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
