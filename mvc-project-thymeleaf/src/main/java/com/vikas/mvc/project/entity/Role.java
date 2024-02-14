package com.vikas.mvc.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "username")
    private String username;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Roles role;

    public Role(){}

    public Role(String username, Roles role) {
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

    @Override
    public String toString() {
        return "Role{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
